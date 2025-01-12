package org.bitmonsters.contentservice.client.feign;

public record FullTagDto(
        Integer id,
        String name,
        String description,
        String icon,
        Long postCount
) {
}
