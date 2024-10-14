package org.bitmonsters.authserver.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PasswordResetConfirmedRequest(
        @NotNull(message = "password is required")
        @NotEmpty(message = "password cannot be empty")
        String password
) {
}
