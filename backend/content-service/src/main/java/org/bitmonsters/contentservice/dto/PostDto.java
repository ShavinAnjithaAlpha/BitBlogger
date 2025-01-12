package org.bitmonsters.contentservice.dto;

import lombok.Builder;
import org.bitmonsters.contentservice.client.feign.TopicDto;
import org.bitmonsters.contentservice.client.feign.UserResponse;

import java.time.LocalDateTime;

@Builder
public record PostDto(
        String id,
        UserResponse author,
        String title,
        String coverImage,
        String preview,
        LocalDateTime publishedAt,
        TopicDto topic,
        Boolean isPinned,
        Boolean isFeatured,
        Integer readingTime
) {
}
