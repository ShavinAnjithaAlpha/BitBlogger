package org.bitmonsters.commentservice.handler;

import java.util.Map;

public record ErrorResponse(
        Map<Object, Object> errors
) {
}
