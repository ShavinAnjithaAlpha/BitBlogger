package org.bitmonsters.userservice.user;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

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
        List<UserLinkResponse> links,
        LocalDateTime createdAt
) {
}
