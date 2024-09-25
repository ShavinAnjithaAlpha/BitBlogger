package org.bitmonsters.topicservice.client.feign;

public record UserResponse(
        Long id,
        String name,
        String username,
        String email,
        String profileImage
) {
}
