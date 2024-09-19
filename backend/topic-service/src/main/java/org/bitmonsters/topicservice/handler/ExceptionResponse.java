package org.bitmonsters.topicservice.handler;

import lombok.Builder;

@Builder
public record ExceptionResponse(
        String error
) {
}
