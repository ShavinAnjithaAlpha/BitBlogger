package org.bitmonsters.mediaservice.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ExceptionResponse(
        String error,
        String status,
        LocalDateTime timestamp
) {
}
