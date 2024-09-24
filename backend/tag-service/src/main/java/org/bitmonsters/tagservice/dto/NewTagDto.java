package org.bitmonsters.tagservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record NewTagDto(
        @NotNull(message = "name is required")
        @NotBlank(message = "name cannot be empty")
        @Pattern(regexp = "^[a-zA-Z0-9_-]{1,100}$", message = "invalid tag format")
        String name,
        @NotNull(message = "description is required")
        @NotBlank(message = "description cannot be empty")
        @Size(max = 1024, message = "too long description")
        String description,
        @Size(max = 1024, message = "icon url cannot be exceeded 1024 characters")
        String icon
) {
}
