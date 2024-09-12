package org.bitmonsters.userservice.me;

import lombok.Builder;
import org.bitmonsters.userservice.user.Location;
import org.bitmonsters.userservice.user.UserProfile;

@Builder
public record UserUpdateDto(
        Long id,
        String username,
        String name,
        String email,
        String profileImage,
        String websiteUrl,
        Location location,
        UserProfile profile
) {
}
