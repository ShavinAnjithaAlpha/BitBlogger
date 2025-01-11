package org.bitmonsters.contentservice.dto;

import lombok.Builder;
import org.bitmonsters.contentservice.client.feign.TopicDto;
import org.bitmonsters.contentservice.client.feign.UserResponse;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.time.LocalDateTime;

@Builder
public record PostDto(
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
