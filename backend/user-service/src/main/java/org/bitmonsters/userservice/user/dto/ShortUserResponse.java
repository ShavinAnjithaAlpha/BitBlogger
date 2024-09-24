package org.bitmonsters.userservice.user.dto;

import lombok.Builder;

@Builder
public record ShortUserResponse(
        Long id,
        String name,
        String username,
        String email,
        String profileImage
) {
}
