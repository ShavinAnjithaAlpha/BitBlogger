package org.bitmonsters.authserver.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AuthenticationRequest(
        @NotNull(message = "email is required")
        @NotEmpty(message = "email cannot be empty")
        @Pattern(regexp="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,10}$", message = "invalid email format")
        String email,
        @NotNull(message = "password is required")
        @NotEmpty(message = "password cannot be empty")
        String password
) {
}
