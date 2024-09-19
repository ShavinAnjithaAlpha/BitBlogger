package org.bitmonsters.topicservice.service;

import org.bitmonsters.topicservice.dto.NewTopicDto;
import org.bitmonsters.topicservice.dto.TopicDto;
import org.bitmonsters.topicservice.dto.TopicHistoryRecordDto;
import org.bitmonsters.topicservice.model.Topic;
import org.bitmonsters.topicservice.model.TopicAction;
import org.bitmonsters.topicservice.model.TopicHistory;
import org.springframework.stereotype.Service;

@Service
public class TopicMapper {

    public Topic toTopic(NewTopicDto newTopicDto) {
        return Topic.builder()
                .name(newTopicDto.name())
                .description(newTopicDto.description())
                .parentTopic(newTopicDto.parentTopicId() == null ? null : Topic.builder().id(newTopicDto.parentTopicId()).build())
                .build();
    }

    public TopicHistory toTopicHistory(Topic topic, TopicAction topicAction, Long userId) {
        return TopicHistory.builder()
                .topic(topic)
                .name(topic.getName())
                .description(topic.getDescription())
                .parentTopicId(topic.getParentTopic() == null ? null : topic.getParentTopic().getId())
                .changedBy(userId)
                .action(topicAction)
                .build();
    }

    public TopicDto toTopicDto(Topic topic) {
        return TopicDto.builder()
                .id(topic.getId())
                .name(topic.getName())
                .description(topic.getDescription())
                .isChild(topic.getParentTopic() != null)
                .isParent(!topic.getChildTopics().isEmpty())
                .build();
    }

    public TopicHistoryRecordDto toTopicHistoryRecordDto(TopicHistory topicHistory) {
        return TopicHistoryRecordDto.builder()
                .id(topicHistory.getId())
                .name(topicHistory.getName())
                .description(topicHistory.getDescription())
                .changedBy(topicHistory.getChangedBy())
                .action(topicHistory.getAction())
                .changedAt(topicHistory.getChangedAt())
                .build();
    }
}
