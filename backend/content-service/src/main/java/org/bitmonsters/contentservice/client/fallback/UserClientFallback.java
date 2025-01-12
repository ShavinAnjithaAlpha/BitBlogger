package org.bitmonsters.contentservice.client.fallback;

import org.bitmonsters.contentservice.client.feign.UserClient;
import org.bitmonsters.contentservice.client.feign.UserResponse;
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
    public List<UserResponse> getUsersByUserIds(List<Long> userIds) {
        return userIds.stream()
                .map(userId -> UserResponse.builder().id(userId).build())
                .toList();
    }
}
