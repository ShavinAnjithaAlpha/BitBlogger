package org.bitmonsters.pollservice.client.feign;

public record TagResponse(
        Integer id,
        String name,
        String description,
        String icon,
        Long postCount
) {
}
