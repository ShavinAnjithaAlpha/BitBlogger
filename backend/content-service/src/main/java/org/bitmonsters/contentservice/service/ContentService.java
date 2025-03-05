package org.bitmonsters.contentservice.service;

import jakarta.ws.rs.NotAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.bitmonsters.contentservice.client.feign.*;
import org.bitmonsters.contentservice.dto.*;
import org.bitmonsters.contentservice.exception.InvalidPostException;
import org.bitmonsters.contentservice.exception.PostNotFountException;
import org.bitmonsters.contentservice.kafka.dto.NewPostBrokerDto;
import org.bitmonsters.contentservice.kafka.dto.PostTagBrokerDto;
import org.bitmonsters.contentservice.kafka.producer.MessageProducer;
import org.bitmonsters.contentservice.model.Post;
import org.bitmonsters.contentservice.model.PostDraft;
import org.bitmonsters.contentservice.repository.PostDraftRepository;
import org.bitmonsters.contentservice.repository.PostRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final PostRepository postRepository;
    private final PostMapper mapper;
    private final PostMapper postMapper;
    private final TagClient tagClient;
    private final PostDraftRepository postDraftRepository;
    private final TopicClient topicClient;
    private final MessageProducer messageProducer;
    private final UserClient userClient;

    public IDResponse createNewPost(NewPostDto newPostDto, Long userId) {
        // check whether the topic exists in the TOPIC-SERVICE
        TopicDto topic = topicClient.getTopic(newPostDto.topicId());

        Post post = postRepository.save(mapper.toPost(newPostDto, userId));

        // TODO: update post count at relevant tags via message broker
        messageProducer.sendMessage("POST-COUNT-INCREMENT", PostTagBrokerDto.builder()
                .postId(post.getId())
                .tags(newPostDto.tagIds())
                .build());

        // TODO: submit tag info to the TAG SERVICE via HTTP client
        tagClient.addTagsToPost(post.getId(), TagList.builder().tags(newPostDto.tagIds()).build());

        // TODO: add the post to the search index via message broker
        // fetch user info from the user client to send to search index along side with the post data
        UserResponse user = userClient.getUserById(userId, true);
        NewPostBrokerDto newPostMessage = NewPostBrokerDto.builder()
                .id(post.getId())
                .author(user)
                .title(post.getTitle())
                .content(post.getContent())
                .preview(post.getPreview())
                .publishedAt(post.getPublishedAt())
                .language(post.getLanguage().name())
                .topic(topic)
                .tags(List.of())
                .build();
        messageProducer.sendMessage("NEW-POST", newPostMessage);

        return IDResponse.builder()
                .id(post.getId())
                .build();
    }

    public void deletePost(String postId, Long userId) {
        // get the post first
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PostNotFountException(postId)
        );

        // check the ownership of the post
        if (!post.getUserId().equals(userId))
            throw new NotAuthorizedException("cannot delete post: " + postId);

        // ownership valid
        // mark it as deleted in the database
        post.setDeleted(Boolean.TRUE);
        postRepository.save(post);

        // remove it from the search index
        // TODO: search index removing via message broker message
    }


    public IDResponse createDraftPost(PostDraftDto postDraftDto, Long userId) {
        PostDraft draft = postDraftRepository.save(mapper.toPostDraft(postDraftDto, userId));

        return IDResponse.builder()
                .id(draft.getId())
                .build();
    }

    public void updateDraftPost(String postId, PostDraftDto postDraftDto, Long userId) {
        // fetch the post from the database and if not exists throw an exception
        PostDraft draft = postDraftRepository.findById(postId).orElseThrow(
                () -> new PostNotFountException(postId)
        );

        // check the ownership of the post
        if (!draft.getUserId().equals(userId)) {
            throw new NotAuthorizedException("cannot edit draft: " + postId);
        }

        // otherwise edit the content of the draft
        draft.setTitle(postDraftDto.title());
        draft.setContent(postDraftDto.content());
        draft.setCoverImage(postDraftDto.coverImage());
        draft.setModifiedAt(LocalDateTime.now());

        // save the changes in the database
        postDraftRepository.save(draft);
    }

    public FullPostDto getPostById(String postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PostNotFountException(postId)
        );

        // fetch tags related to post from the TAG-SERVICE via feign client
        List<FullTagDto> tags = tagClient.getTagOfPost(postId);
        return mapper.toFullPostDto(post, tags);
    }

    public List<PostDto> getLatestPosts(Pageable page) {
        return postRepository.findAllByDeleted(false, page)
                .map(postMapper::toPostDto)
                .toList();
    }

    public List<PostDto> getPostsByUserId(Long userId, Pageable page) {
        return postRepository.findByUserIdAndDeleted(userId, false, page)
                .map(postMapper::toPostDto)
                .toList();
    }


    public List<PostDto> getPostsByTopicId(Integer topicId, Pageable page) {
        return postRepository.findAllByTopicId(topicId, page)
                .map(postMapper::toPostDto)
                .stream().toList();
    }

    @Transactional
    public IDResponse createPostFromDraft(String draftId, PostFromDraftDto postFromDraftDto, Long userId) {
        // fetch the draft from the database
        PostDraft draft = postDraftRepository.findById(draftId).orElseThrow(
                () -> new PostNotFountException(draftId)
        );

        // check the ownership of the draft with the user try to publish as a post
        if (!draft.getUserId().equals(userId))
            throw new NotAuthorizedException("cannot published draft: " + draftId);

        // check whether title and content is not blank from the draft
        if (draft.getTitle().isBlank() || draft.getContent().isBlank())
            throw new InvalidPostException("title and content must be not empty");

        // create a new post and published it
        IDResponse response =  createNewPost(mapper.toNewPostDto(draft, postFromDraftDto), userId);

        // delete the draft from the database
        postDraftRepository.deleteById(draftId);

        return response;
    }
}
