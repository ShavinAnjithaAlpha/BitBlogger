package org.bitmonsters.contentservice.client.feign;

public record TopicDto(
        Integer id,
        String name,
        String description,
        Boolean isParent,
        Boolean isChild
) {
}
