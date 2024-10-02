package org.bitmonsters.pollservice.client.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.bitmonsters.pollservice.client.config.FeignUserClientConfig;
import org.bitmonsters.pollservice.client.fallback.UserClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "USER-SERVICE", configuration = FeignUserClientConfig.class, fallback = UserClientFallback.class)
public interface UserClient {

    @CircuitBreaker(name = "USER-SERVICE", fallbackMethod = "fallbackGetUserById")
    @GetMapping("/api/v1/users/{userId}")
    UserResponse getUserById(@PathVariable("userId") Long userId, @RequestParam("short") Boolean isShort);

    @CircuitBreaker(name = "USER-SERVICE", fallbackMethod = "fallbackGetUsersByIDs")
    @PostMapping("/api/v1/users/batch")
    List<UserResponse> getUsersByUserIDs(@RequestBody List<Long> userIds);

    default UserResponse fallbackGetUserById(Throwable throwable) {
        return UserResponse.builder()
                .build();
    }

    default List<UserResponse> fallbackGetUsersByIDs(Throwable throwable) {
        return List.of();
    }
}
