package org.bitmonsters.topicservice.dto;

import lombok.Builder;
import org.bitmonsters.topicservice.model.TopicAction;

import java.time.LocalDateTime;

@Builder
public record TopicHistoryRecordDto(
        Integer id,
        String name,
        String description,
        Long changedBy,
        TopicAction action,
        LocalDateTime changedAt
) {
}
