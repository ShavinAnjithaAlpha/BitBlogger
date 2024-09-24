package org.bitmonsters.tagservice.client.feign;

import lombok.Builder;

@Builder
public record UserResponse(
        Long id,
        String username,
        String email,
        String name,
        String profileImage
) {
}
