package org.bitmonsters.userservice.platform.dto;

import lombok.Builder;

@Builder
public record PlatformDto(
        Integer id,
        String name,
        String baseUrl,
        String description
) {
}
