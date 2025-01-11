package org.bitmonsters.contentservice.service;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.contentservice.client.feign.TagClient;
import org.bitmonsters.contentservice.client.feign.TopicClient;
import org.bitmonsters.contentservice.client.feign.UserClient;
import org.bitmonsters.contentservice.dto.FullPostDto;
import org.bitmonsters.contentservice.dto.NewPostDto;
import org.bitmonsters.contentservice.dto.PostDto;
import org.bitmonsters.contentservice.model.Language;
import org.bitmonsters.contentservice.model.Post;
import org.bitmonsters.contentservice.model.PostMetaData;
import org.bitmonsters.contentservice.model.PostStatus;
import org.bitmonsters.contentservice.util.PostUtils;
import org.bitmonsters.contentservice.util.SanitizationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostMapper {

    private final UserClient userClient;
    private final TopicClient topicClient;
    private final TagClient tagClient;
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

    public FullPostDto toFullPostDto(Post post) {
        return FullPostDto.builder()
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
                .build();
    }

    public PostDto toPostDto(Post post) {
        return PostDto.builder()
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


}
