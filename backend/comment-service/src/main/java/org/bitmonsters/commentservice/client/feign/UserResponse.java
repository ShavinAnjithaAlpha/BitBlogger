package org.bitmonsters.commentservice.client.feign;

import lombok.Builder;

@Builder
public record UserResponse(
        Long id,
        String name,
        String username,
        String email,
        String profileImage
) {
}
