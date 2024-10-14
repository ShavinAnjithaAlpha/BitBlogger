package org.bitmonsters.authserver.handler;

import java.util.HashMap;

public record ErrorResponse(
        HashMap<Object, Object> errors) {
}
