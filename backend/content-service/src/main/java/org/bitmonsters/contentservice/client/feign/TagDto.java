package org.bitmonsters.contentservice.client.feign;

import lombok.Builder;

@Builder
public record TagDto(
        Integer id,
        String name
) {
}
