package org.bitmonsters.contentservice.client.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.bitmonsters.contentservice.client.config.FeignClientConfiguration;
import org.bitmonsters.contentservice.client.fallback.UserClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@FeignClient(value = "USER-SERVICE", fallback = UserClientFallback.class, configuration = FeignClientConfiguration.class)
public interface UserClient {

    @CircuitBreaker(name = "USER-SERVICE", fallbackMethod = "fallbackGetUserById")
    @GetMapping("/api/v1/users/{userId}")
    UserResponse getUserById(@PathVariable("userId") Long userId, @RequestParam("short") Boolean isShort);

    @CircuitBreaker(name = "USER-SERVICE", fallbackMethod = "fallbackGetUsersByUserIds")
    @PostMapping("/api/v1/users/batch")
    List<UserResponse> getUsersByUserIds(@RequestBody List<Long> userIds);

    default UserResponse fallbackGetUserById(@PathVariable("userId") Long userId, @RequestParam("short") Boolean isShort) {
        return UserResponse.builder()
                .id(userId)
                .build();
    }

    default List<UserResponse> fallbackGetUsersByUserIds(@RequestBody List<Long> userIds) {
        List<UserResponse> users = new ArrayList<>(userIds.size());
        for (Long userId: userIds) {
            users.add(
                    UserResponse.builder()
                            .id(userId)
                            .build()
            );
        }

        return users;
    }
}
