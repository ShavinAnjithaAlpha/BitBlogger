package org.bitmonsters.topicservice.controller;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.topicservice.dto.IDResponse;
import org.bitmonsters.topicservice.dto.NewTopicDto;
import org.bitmonsters.topicservice.dto.TopicDto;
import org.bitmonsters.topicservice.dto.TopicHistoryDto;
import org.bitmonsters.topicservice.service.TopicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService service;

    @PostMapping
    public ResponseEntity<IDResponse> addNewTopic(
            Authentication authentication,
            @Validated @RequestBody NewTopicDto newTopicDto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.addNewTopic((Long) authentication.getPrincipal(), newTopicDto));
    }

    @PutMapping("/{topicId}")
    public ResponseEntity<?> updateTopic(
            @PathVariable("topicId") Integer topicId,
            Authentication authentication,
            @Validated @RequestBody NewTopicDto newTopicDto
    ) {
        service.updateTopic(topicId, (Long) authentication.getPrincipal(), newTopicDto);
        return ResponseEntity.ok(null);
    }

    @GetMapping
    public Page<TopicDto> getTopics(
            Pageable page
    ) {
        return service.getTopics(page);
    }

    @GetMapping("/{topicId}")
    public TopicDto getTopic(
            @PathVariable("topicId") Integer topicId
    ) {
        return service.getTopic(topicId);
    }

    @GetMapping("/{topicId}/parent")
    public TopicDto getParentTopic(
            @PathVariable("topicId") Integer topicId
    ) {
        return service.getParent(topicId);
    }

    @GetMapping("/{topicId}/subtopics")
    public List<TopicDto> getSubTopics(
            @PathVariable("topicId") Integer topicId
    ) {
        return service.getSubTopics(topicId);
    }

    @GetMapping("/{topicId}/history")
    public TopicHistoryDto getTopicHistory(
            @PathVariable("topicId") Integer topicId
    ) {
        return service.getTopicHistory(topicId);
    }
    

}
