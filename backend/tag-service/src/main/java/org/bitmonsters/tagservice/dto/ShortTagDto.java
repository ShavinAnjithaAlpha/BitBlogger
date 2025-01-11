package org.bitmonsters.tagservice.dto;

import lombok.Builder;

@Builder
public record ShortTagDto(
        Integer id,
        String name
) {
}
