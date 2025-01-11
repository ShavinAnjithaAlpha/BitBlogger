package org.bitmonsters.contentservice.service;

import jakarta.ws.rs.NotAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.bitmonsters.contentservice.dto.*;
import org.bitmonsters.contentservice.exception.PostNotFountException;
import org.bitmonsters.contentservice.model.Post;
import org.bitmonsters.contentservice.repository.PostRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final PostRepository postRepository;
    private final PostMapper mapper;
    private final PostMapper postMapper;

    public IDResponse createNewPost(NewPostDto newPostDto, Long userId) {
        String postId = postRepository.save(mapper.toPost(newPostDto, userId)).getId();

        // TODO: update post count at relevant tags via message broker

        return IDResponse.builder()
                .id(postId)
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
        return null;
    }

    public FullPostDto getPostById(String postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PostNotFountException(postId)
        );
        return mapper.toFullPostDto(post);
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
}
