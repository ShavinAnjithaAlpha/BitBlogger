package org.bitmonsters.contentservice.handler;

import lombok.Builder;

@Builder
public record ExceptionResponse(
        String error
) {
}
