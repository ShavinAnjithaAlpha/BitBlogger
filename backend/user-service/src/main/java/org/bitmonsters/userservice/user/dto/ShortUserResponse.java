package org.bitmonsters.userservice.user.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record ShortUserResponse(
        Long id,
        String name,
        String username,
        String email,
        String profileImage
) implements Serializable {
}
