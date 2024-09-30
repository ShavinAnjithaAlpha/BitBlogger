package org.bitmonsters.pollservice.client.feign;

public record UserResponse(
        Long id,
        String name,
        String username,
        String email,
        String profileImage
) {
}
