package org.bitmonsters.tagservice.dto;

import lombok.Builder;

@Builder
public record TagDto(
        Integer id,
        String name,
        String description,
        String icon
) {
}
