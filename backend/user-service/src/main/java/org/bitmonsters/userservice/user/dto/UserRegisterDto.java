package org.bitmonsters.userservice.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserRegisterDto(
        @NotNull(message = "name is required")
        @NotBlank(message = "name cannot be blank")
        String name,

        @NotNull(message = "username required")
        @NotBlank(message = "username cannot be empty")
        @Pattern(regexp = "^[A-Za-z0-9+_.-]{3,50}$", message = "username must be 3-50 characters")
        String username,

        @NotBlank(message = "email cannot be empty")
        @NotNull(message = "email required")
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid email format")
        String email,

        @NotNull(message = "password is required")
        @NotBlank(message = "password cannot be empty")
        @Pattern(regexp = "^.{8,}$", message = "invalid password format")
        String password
) {
}
