package org.bitmonsters.topicservice.service;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.topicservice.client.feign.UserClient;
import org.bitmonsters.topicservice.client.feign.UserResponse;
import org.bitmonsters.topicservice.dto.*;
import org.bitmonsters.topicservice.exception.TopicAlreadyExistsException;
import org.bitmonsters.topicservice.exception.TopicNotExistsException;
import org.bitmonsters.topicservice.model.Topic;
import org.bitmonsters.topicservice.model.TopicAction;
import org.bitmonsters.topicservice.model.TopicHistory;
import org.bitmonsters.topicservice.repository.TopicHistoryRepository;
import org.bitmonsters.topicservice.repository.TopicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicHistoryRepository topicHistoryRepository;
    private final TopicMapper mapper;
    private final UserClient userClient;

    public IDResponse addNewTopic(Long userId, NewTopicDto newTopicDto) {
        // check whether there is topic with same name
        if (isTopicExists(newTopicDto.name())) {
            throw new TopicAlreadyExistsException(String.format("topic with name %s is already exists", newTopicDto.name()));
        }

        // check if the parent topic exists if provided
        if (newTopicDto.parentTopicId() != null && !isTopicExists(newTopicDto.parentTopicId())) {
            throw new TopicNotExistsException(String.format("parent topic with id %d is not exists", newTopicDto.parentTopicId()));
        }

        // otherwise save the new topic
        var newTopic = topicRepository.save(mapper.toTopic(newTopicDto));
        // create topic history record on creation of the topic
        topicHistoryRepository.save(mapper.toTopicHistory(newTopic, TopicAction.TOPIC_CREATED, userId));

        // return the ID of the created topic
        return IDResponse.builder()
                .id(newTopic.getId())
                .build();
    }

    private boolean isTopicExists(String name) {
        var topic = topicRepository.findByName(name);
        return topic.isPresent();
    }

    private boolean isTopicExists(Integer topicId) {
        var topic = topicRepository.findById(topicId);
        return topic.isPresent();
    }

    public void updateTopic(Integer topicId, Long userId, NewTopicDto newTopicDto) {
        var topic = topicRepository.findById(topicId).orElseThrow(
                () -> new TopicNotExistsException(String.format("topic with id %d is not exists", topicId))
        );

        // check whether if parent topic is valid given provided
        if (newTopicDto.parentTopicId() != null && !isTopicExists(newTopicDto.parentTopicId())) {
            throw new TopicNotExistsException(String.format("parent topic with id %d is not exists", newTopicDto.parentTopicId()));
        }

        // determine the action on the topic
        TopicAction action = TopicAction.TOPIC_CREATED;
        if (!topic.getName().equals(newTopicDto.name())) action = action.addAction(TopicAction.NAME_CHANGED);
        if (!topic.getDescription().equals(newTopicDto.description())) action = action.addAction(TopicAction.DESCRIPTION_CHANGED);
        if (!Objects.equals(topic.getParentTopic().getId(), newTopicDto.parentTopicId())) action = action.addAction(TopicAction.PARENT_CHANGED);

        // update the certain fields
        topic.setName(newTopicDto.name());
        topic.setDescription(newTopicDto.description());
        topic.setParentTopic(Topic.builder().id(newTopicDto.parentTopicId()).build());
        topicRepository.save(topic);

        // create a history record for the topic
        topicHistoryRepository.save(mapper.toTopicHistory(topic, action, userId));
    }

    public Page<TopicDto> getTopics(Pageable page) {
        return topicRepository.findAll(page)
                .map(mapper::toTopicDto);
    }

    public TopicDto getTopic(Integer topicId) {
        return mapper.toTopicDto(topicRepository.findById(topicId).orElseThrow(
                () -> new TopicNotExistsException(String.format("topic with id %d is not exists", topicId))
        ));
    }

    public TopicDto getParent(Integer topicId) {
        var topic = topicRepository.findById(topicId).orElseThrow(
                () -> new TopicNotExistsException(String.format("topic with id %d is not exists", topicId))
        );

        // get the parent
        Topic parentTopic = topic.getParentTopic();
        if (parentTopic == null) return null;
        else return mapper.toTopicDto(parentTopic);
    }

    public List<TopicDto> getSubTopics(Integer topicId) {
        var topic = topicRepository.findById(topicId).orElseThrow(
                () -> new TopicNotExistsException(String.format("topic with id %d is not exists", topicId))
        );

        return topic.getChildTopics().stream()
                .map(mapper::toTopicDto)
                .collect(Collectors.toList());
    }

    public TopicHistoryDto getTopicHistory(Integer topicId) {
        var topic = topicRepository.findById(topicId).orElseThrow(
                () -> new TopicNotExistsException(String.format("topic with id %d is not exists", topicId))
        );

        // get the topic history items from the topic id
        List<TopicHistory> history = topicHistoryRepository.findAllByTopicId(topicId);
        // get the user ids from the history
        List<Long> userIds = history.stream().map(TopicHistory::getChangedBy).toList();
        // fetch the batch of users from the batch endpoint of the user client
        List<UserResponse> users = userClient.getUsersByUserIds(userIds);

        return TopicHistoryDto.builder()
                .topic(mapper.toTopicDto(topic))
                .history(history.stream()
                        .map(
                                topicHistory -> mapper.toTopicHistoryRecordDto(topicHistory,
                                        users.stream()
                                                .filter(user -> user.id().equals(topicHistory.getChangedBy()))
                                                .findFirst()
                                                .orElse(UserResponse.builder().build()))
                        )
                        .collect(Collectors.toList()))
                .build();
    }
}
