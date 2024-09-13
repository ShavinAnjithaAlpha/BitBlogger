package org.bitmonsters.userservice.platform;

import lombok.Builder;

@Builder
public record PlatformDto(
        Integer id,
        String name,
        String baseUrl,
        String description
) {
}
