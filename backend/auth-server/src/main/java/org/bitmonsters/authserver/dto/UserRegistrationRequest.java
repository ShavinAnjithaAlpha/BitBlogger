package org.bitmonsters.authserver.dto;

public record UserRegistrationRequest(
        String name,
        String username,
        String email,
        String password
) {
}
