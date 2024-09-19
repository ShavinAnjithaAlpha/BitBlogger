package org.bitmonsters.userservice.platform.dto;

import jakarta.validation.constraints.*;

public record NewPlatformDto(
        @NotNull(message = "platform name is required")
        @NotBlank(message = "platform name cannot be blank")
        String name,
        @Pattern(regexp = "^[A-Za-z0-9+_.-:/]+.[A-Za-z]+$", message = "Invalid URL format")
        String baseUrl,
        @Size(max = 250, message = "description must be maximum 250 characters")
        String description
) {
}
