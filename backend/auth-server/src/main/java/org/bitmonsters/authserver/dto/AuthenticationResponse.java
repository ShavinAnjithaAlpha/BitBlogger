package org.bitmonsters.authserver.dto;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
    String token
) {
}
