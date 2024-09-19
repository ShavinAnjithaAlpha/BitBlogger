package org.bitmonsters.userservice.me.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record FollowerResponse(
        Long id,
        String username,
        String email,
        String name,
        String profileImage,
        String websiteUrl,
        LocalDateTime followedAt
) {
}
