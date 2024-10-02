package org.bitmonsters.pollservice.client.fallback;

import org.bitmonsters.pollservice.client.feign.UserClient;
import org.bitmonsters.pollservice.client.feign.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserClientFallback implements UserClient {

    @Override
    public UserResponse getUserById(Long userId, Boolean isShort) {
        return UserResponse.builder()
                .id(userId)
                .build();
    }

    @Override
    public List<UserResponse> getUsersByUserIDs(List<Long> userIds) {
        return List.of();
    }
}
