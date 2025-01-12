package org.bitmonsters.tagservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bitmonsters.tagservice.client.feign.UserClient;
import org.bitmonsters.tagservice.dto.*;
import org.bitmonsters.tagservice.exception.TagAlreadyExistsException;
import org.bitmonsters.tagservice.exception.TagNotFoundException;
import org.bitmonsters.tagservice.exception.TooMuchTagsException;
import org.bitmonsters.tagservice.model.PostTag;
import org.bitmonsters.tagservice.model.TagAction;
import org.bitmonsters.tagservice.repository.PostTagRepository;
import org.bitmonsters.tagservice.repository.TagHistoryRepository;
import org.bitmonsters.tagservice.repository.TagRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {

    public static final Integer MAXIMUM_TAG_PER_POST = 8;

    private final TagRepository tagRepository;
    private final TagHistoryRepository tagHistoryRepository;
    private final PostTagRepository postTagRepository;
    private final TagMapper mapper;

    @Transactional
    public IdResponse createTag(NewTagDto newTagDto, Long userId) {
        if (tagExists(newTagDto.name())) {
            throw new TagAlreadyExistsException(String.format("tag with name %s is already exists", newTagDto.name()))
;        }

        // create a tag with the specified fields
        var tag = tagRepository.save(mapper.toTag(newTagDto));
        // create a history record on the creation of the tag
        tagHistoryRepository.save(mapper.toTagHistory(tag, TagAction.TAG_CREATED, userId));

        return new IdResponse(tag.getId());
    }

    private boolean tagExists(String name) {
        return tagRepository.findByName(name).isPresent();
    }

    @Transactional
    public void updateTag(Integer tagId, NewTagDto newTagDto, Long userId) {
        var tag = tagRepository.findById(tagId).orElseThrow(
                () -> new TagNotFoundException(String.format("tag with id %d is not found", tagId))
        );

        var tagAction = TagAction.TAG_CREATED;
        if (!newTagDto.name().equals(tag.getName())) tagAction = tagAction.addAction(TagAction.NAME_CHANGED);
        if (!newTagDto.description().equals(tag.getDescription())) tagAction = tagAction.addAction(TagAction.DESCRIPTION_CHANGED);
        if (!((newTagDto.icon() == null && tag.getIcon() == null) || (Objects.equals(newTagDto.icon(), tag.getIcon())))) tagAction = tagAction.addAction(TagAction.ICON_CHANGED);

        // update the tag
        tag.setName(newTagDto.name());
        tag.setDescription(newTagDto.description());
        tag.setIcon(newTagDto.icon());
        tagRepository.save(tag);

        // create a history record
        tagHistoryRepository.save(mapper.toTagHistory(tag, tagAction, userId));
    }

    public void deleteTag(Integer tagId, Long userId) {
        tagRepository.deleteById(tagId);
    }

    public Slice<TagDto> getTags(Pageable page) {
        return tagRepository.findAll(page).map(mapper::toTagDto);
    }

    public TagDto getTag(Integer tagId) {
        return mapper.toTagDto(tagRepository.findById(tagId).orElseThrow(
                () -> new TagNotFoundException(String.format("tag with id %d is not found", tagId))
        ));
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public void addTagToPost(String postId, Integer tagId, Long userId) {
        // TODO: check whether if the post exists before add the tag

        // get the number of tags of the post
        Integer tagCount = getTagCount(postId);
        if (tagCount >= MAXIMUM_TAG_PER_POST) throw new TooMuchTagsException(postId, tagCount, 1);

        var tag = tagRepository.findById(tagId).orElseThrow(
                () -> new TagNotFoundException(String.format("tag with id %d is not found", tagId))
        );

        // create new tag for the post
        postTagRepository.save(mapper.toPostTag(postId, tag));

        // increase the post count in the tag item
        tag.setPostCount(tag.getPostCount() + 1);
        tagRepository.save(tag);
    }

    @Transactional
    public void removeTagFromPost(String postId, Integer tagId, Long userId) {
        // TODO: check whether user owns the post

        // remove the post tag from the system
        postTagRepository.deleteByPostIdAndTagId(postId, tagId);

        // decrease the post count from the tag item
        var tag = tagRepository.findById(tagId).orElse(null);
        if (tag != null) {
            tag.setPostCount(tag.getPostCount() - 1);
            tagRepository.save(tag);
        }
    }

    public List<TagDto> getTagsOfPost(String postId) {
        return postTagRepository.findAllByPostId(postId).stream()
                .map(mapper::toTagDto)
                .collect(Collectors.toList());
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public void addTagsToPost(String postId, Long userId, TagList tagList) {
        // TODO: check whether if the post exists and owns by the authenticated user

        // get the number of stored tag with post
        Integer tagCount = getTagCount(postId);
        if (tagCount + tagList.tags().size() > MAXIMUM_TAG_PER_POST)
            throw new TooMuchTagsException(postId, tagCount, tagList.tags().size());

        // go through the tag list and add each valid tags to the post
        for (var tagId : tagList.tags()) {
            // check the existence
            var tag = tagRepository.findById(tagId).orElseThrow(
                    () -> new TagNotFoundException(String.format("tag with id %d is not found", tagId))
            );

            if (postTagRepository.findByTagIdAndPostId(tagId, postId).isPresent())
                continue;

            // add the tag to the post
            postTagRepository.save(mapper.toPostTag(postId, tag));

            // increase the post count on each tag record by one
            tag.setPostCount(tag.getPostCount() + 1);
            tagRepository.save(tag);
        }
    }

    private Integer getTagCount(String postId) {
        return postTagRepository.countAllByPostId(postId);
    }

    public Slice<String> getPostsOfTag(Integer tagId) {
        return postTagRepository.findAllByTagId(tagId).map(PostTag::getPostId);
    }

    public TagHistoryDto getTagHistory(Integer tagId, Long userId) {
        var tag = tagRepository.findById(tagId).orElseThrow(
                () -> new TagNotFoundException(String.format("tag with id %d is not found", tagId))
        );

        return mapper.toTagHistoryDto(tag,
                tagHistoryRepository.findAllByTagId(tagId));
    }
}
