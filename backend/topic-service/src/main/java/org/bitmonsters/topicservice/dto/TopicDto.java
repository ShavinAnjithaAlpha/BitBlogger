package org.bitmonsters.topicservice.dto;

import lombok.Builder;


@Builder
public record TopicDto(
        Integer id,
        String name,
        String description,
        Boolean isParent,
        Boolean isChild
) {
}
