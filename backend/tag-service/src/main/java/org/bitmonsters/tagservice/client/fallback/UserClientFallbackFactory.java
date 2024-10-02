package org.bitmonsters.tagservice.client.fallback;

import feign.FeignException;
import org.bitmonsters.tagservice.client.feign.UserClient;
import org.bitmonsters.tagservice.client.feign.UserResponse;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallbackFactory implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable cause) {
        return new UserClient() {
            @Override
            public UserResponse getUserByID(Long userId, Boolean isShort) {
                if (cause instanceof FeignException.ServiceUnavailable) {
                    return UserResponse.builder().id(userId).build();
                }

                return UserResponse.builder().build();
            }
        };
    }
}
