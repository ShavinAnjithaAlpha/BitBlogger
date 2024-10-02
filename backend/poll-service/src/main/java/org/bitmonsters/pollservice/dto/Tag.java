package org.bitmonsters.pollservice.dto;

import lombok.Builder;

@Builder
public record Tag(
        Integer id,
        String name
) {
}
