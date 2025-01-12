package org.bitmonsters.contentservice.service;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.contentservice.client.feign.FullTagDto;
import org.bitmonsters.contentservice.client.feign.TopicClient;
import org.bitmonsters.contentservice.client.feign.UserClient;
import org.bitmonsters.contentservice.dto.*;
import org.bitmonsters.contentservice.model.*;
import org.bitmonsters.contentservice.util.PostUtils;
import org.bitmonsters.contentservice.util.SanitizationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostMapper {

    private final UserClient userClient;
    private final TopicClient topicClient;
    private final SanitizationService sanitizationService;
    private final PostUtils postUtils;

    public Post toPost(NewPostDto newPostDto, Long userId) {
        String sanitizedContent = sanitizationService.sanitizeInput(newPostDto.content());
        return Post.builder()
                .userId(userId)
                .title(sanitizationService.sanitizeInput(newPostDto.title()))
                .content(sanitizedContent)
                .coverImage(newPostDto.coverImage())
                .preview(newPostDto.preview() != null ? sanitizationService.sanitizeInput(newPostDto.preview()) : sanitizedContent.chars()
                        .limit(100)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString())
                .status(PostStatus.PUBLISHED)
                .createdAt(LocalDateTime.now())
                .publishedAt(LocalDateTime.now())
                .language(newPostDto.language() != null ? Language.valueOf(newPostDto.language()) : Language.ENGLISH)
                .topicId(newPostDto.topicId())
                .metaData(PostMetaData.builder()
                        .isPinned(newPostDto.isPinned() ? newPostDto.isPinned() : Boolean.FALSE)
                        .isFeatured(Boolean.FALSE)
                        .viewCount(0L)
                        .readingTime(postUtils.readingTime(sanitizedContent))
                        .rating(0)
                        .build())
                .deleted(Boolean.FALSE)
                .build();
    }

    public FullPostDto toFullPostDto(Post post, List<FullTagDto> tags) {
        return FullPostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(userClient.getUserById(post.getUserId(), true))
                .coverImage(post.getCoverImage())
                .preview(post.getPreview())
                .publishedAt(post.getPublishedAt())
                .isFeatured(post.getMetaData().getIsFeatured())
                .isPinned(post.getMetaData().getIsPinned())
                .readingTime(post.getMetaData().getReadingTime())
                .topic(topicClient.getTopic(post.getTopicId()))
                .tags(tags)
                .build();
    }

    public PostDto toPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .author(userClient.getUserById(post.getUserId(), false))
                .title(post.getTitle())
                .preview(post.getPreview())
                .coverImage(post.getCoverImage())
                .publishedAt(post.getPublishedAt())
                .readingTime(post.getMetaData().getReadingTime())
                .isPinned(post.getMetaData().getIsPinned())
                .isFeatured(post.getMetaData().getIsFeatured())
                .topic(topicClient.getTopic(post.getTopicId()))
                .build();
    }


    public PostDraft toPostDraft(PostDraftDto postDraftDto, Long userId) {
        return PostDraft.builder()
                .userId(userId)
                .title(postDraftDto.title())
                .content(postDraftDto.content())
                .coverImage(postDraftDto.coverImage())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public NewPostDto toNewPostDto(PostDraft draft, PostFromDraftDto postFromDraftDto) {
        return NewPostDto.builder()
                .title(draft.getTitle())
                .content(draft.getContent())
                .coverImage(draft.getCoverImage())
                .preview(postFromDraftDto.preview())
                .language(postFromDraftDto.language())
                .isPinned(postFromDraftDto.isPinned())
                .topicId(postFromDraftDto.topicId())
                .tagIds(postFromDraftDto.tagIds())
                .build();
    }
}
