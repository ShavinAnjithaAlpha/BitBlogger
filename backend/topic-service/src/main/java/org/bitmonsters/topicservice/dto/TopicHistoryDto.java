package org.bitmonsters.topicservice.dto;

import lombok.Builder;
import org.bitmonsters.topicservice.model.TopicHistory;

import java.util.List;

@Builder
public record TopicHistoryDto(
        TopicDto topic,
        List<TopicHistoryRecordDto> history
) {
}
