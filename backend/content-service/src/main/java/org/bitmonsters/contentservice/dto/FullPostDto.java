package org.bitmonsters.contentservice.dto;

import lombok.Builder;
import org.bitmonsters.contentservice.client.feign.FullTagDto;
import org.bitmonsters.contentservice.client.feign.TopicDto;
import org.bitmonsters.contentservice.client.feign.UserResponse;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record FullPostDto(
        String id,
        UserResponse author,
        String title,
        String content,
        String coverImage,
        String preview,
        LocalDateTime publishedAt,
        TopicDto topic,
        Boolean isPinned,
        Boolean isFeatured,
        Integer readingTime,
        List<FullTagDto> tags
) {
}
