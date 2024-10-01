package org.bitmonsters.pollservice.client.feign;

import lombok.Builder;

@Builder
public record TagResponse(
        Integer id,
        String name,
        String description,
        String icon,
        Long postCount
) {
}
