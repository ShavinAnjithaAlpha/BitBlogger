package org.bitmonsters.userservice.handler;

import java.util.Map;

public record ErrorResponse(
        Map<Object, Object> errors
) {
}
