package org.bitmonsters.contentservice.handler;

import java.util.Map;

public record ErrorResponse(
        Map<Object, Object> errors
) {
}
