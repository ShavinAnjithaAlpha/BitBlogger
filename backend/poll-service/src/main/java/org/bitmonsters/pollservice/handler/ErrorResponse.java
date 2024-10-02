package org.bitmonsters.pollservice.handler;

import java.util.Map;

public record ErrorResponse(
        Map<Object, Object> errors
) {
}
