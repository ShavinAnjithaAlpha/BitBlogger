package org.bitmonsters.authserver.dto;

public record AuthenticationRequest(
        String email,
        String password
) {
}
