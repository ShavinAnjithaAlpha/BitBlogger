package org.bitmonsters.authserver.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserRegistrationRequest(
        @NotNull(message = "name is required")
        @NotEmpty(message = "name cannot be empty")
        @Pattern(regexp = "[a-zA-Z]{3,100}", message = "name should only contain characters upto 100")
        String name,
        @NotNull(message = "username is required")
        @NotEmpty(message = "username cannot be empty")
        @Pattern(regexp = "^(?![_-])(?!.*[_-]{2})[a-zA-Z0-9_-]{3,20}(?<![_-])$", message = "invalid username format")
        String username,
        @NotNull(message = "email is required")
        @NotEmpty(message = "email cannot be empty")
        @Pattern(regexp="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,10}$", message = "invalid email format")
        String email,
        @NotNull(message = "password is required")
        @NotEmpty(message = "password cannot be empty")
        String password
) {
}
