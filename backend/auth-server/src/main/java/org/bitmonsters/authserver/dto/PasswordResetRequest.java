package org.bitmonsters.authserver.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PasswordResetRequest(
        @NotNull(message = "email is required")
        @NotEmpty(message = "email cannot be empty")
        @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z]+.[a-zA-Z0-9]+", message = "invalid email format")
        String email
) {
}
