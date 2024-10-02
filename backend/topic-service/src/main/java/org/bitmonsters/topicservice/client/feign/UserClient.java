package org.bitmonsters.topicservice.client.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.bitmonsters.topicservice.client.config.FeignUserClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "USER-SERVICE", configuration = FeignUserClientConfig.class)
public interface UserClient {

    @CircuitBreaker(name = "USER-SERVICE", fallbackMethod = "fallbackGetUserById")
    @GetMapping("/api/v1/users/{userId}")
    UserResponse getUserById(@PathVariable("userId") Long userId, @RequestParam("short") Boolean isShort);

    @CircuitBreaker(name = "USER-SERVICE", fallbackMethod = "fallbackGetUsersByUserIds")
    @PostMapping("/api/v1/users/batch")
    List<UserResponse> getUsersByUserIds(@RequestBody List<Long> userIds);

    default UserResponse fallbackGetUserById(Throwable throwable) {
        return UserResponse.builder().build();
    }

    default List<UserResponse> fallbackGetUsersByUserIds(Throwable throwable) {
        return List.of();
    }

}
