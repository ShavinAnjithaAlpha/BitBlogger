package org.bitmonsters.mediaservice.dto;

import lombok.Builder;

@Builder
public record MediaObject(
        String id,
        String url
) {
}
