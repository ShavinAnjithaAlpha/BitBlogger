package org.bitmonsters.tagservice.client.fallback;

import org.bitmonsters.tagservice.client.feign.UserClient;
import org.bitmonsters.tagservice.client.feign.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {

    @Override
    public UserResponse getUserByID(Long userId, Boolean isShort) {
        return UserResponse.builder()
                .id(userId)
                .build();
    }
}
