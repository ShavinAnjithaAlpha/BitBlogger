package org.bitmonsters.userservice.me.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.bitmonsters.userservice.user.model.Location;
import org.bitmonsters.userservice.user.model.UserProfile;

@Builder
public record UserUpdateDto(
        @NotNull(message = "username required")
        @NotBlank(message = "username cannot be empty")
        @Pattern(regexp = "^[A-Za-z0-9+_.-]{3,50}$", message = "username must be 3-50 characters")
        String username,
        @NotNull(message = "name is required")
        @NotBlank(message = "name cannot be blank")
        String name,
        @NotBlank(message = "email cannot be empty")
        @NotNull(message = "email required")
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid email format")
        String email,
        String profileImage,
        String websiteUrl,
        Location location,
        UserProfile profile
) {
}
