package org.bitmonsters.userservice.user.dto;

import lombok.Builder;
import org.bitmonsters.userservice.user.model.Location;
import org.bitmonsters.userservice.user.model.UserProfile;

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
