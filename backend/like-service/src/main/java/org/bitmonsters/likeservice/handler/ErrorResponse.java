package org.bitmonsters.likeservice.handler;

import java.util.Map;

public record ErrorResponse(
        Map<Object, Object> errors
) {
}
