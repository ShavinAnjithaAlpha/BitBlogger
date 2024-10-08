package org.bitmonsters.mediaservice.dto;

import lombok.Builder;

import java.net.URL;

@Builder
public record MediaID(
        MediaObject media,
        MediaObject thumbnail
) {
}
