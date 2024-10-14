package org.bitmonsters.authserver.handler;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record ExceptionResponse(
        String error,
        HttpStatus status,
        LocalDateTime timestamp,
        String description
) {
}
