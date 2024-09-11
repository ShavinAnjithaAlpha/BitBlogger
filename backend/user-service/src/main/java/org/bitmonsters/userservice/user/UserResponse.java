package org.bitmonsters.userservice.user;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserResponse(
        Long id,
        String username,
        String email,
        String name,
        String profileImage,
        String websiteUrl,
        Location location,
        UserProfile profile,
        LocalDateTime createdAt
) {
}
