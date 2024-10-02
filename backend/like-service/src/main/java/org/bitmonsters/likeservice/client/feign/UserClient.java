package org.bitmonsters.likeservice.client.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.bitmonsters.likeservice.client.config.FeignUserClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "USER-SERVICE", configuration = FeignUserClientConfig.class)
public interface UserClient {

    @CircuitBreaker(name = "USER-SERVICE", fallbackMethod = "fallbackGetUsersByUserIDs")
    @PostMapping("/api/v1/users/batch")
    List<UserResponse> getUsersByUserIDs(@RequestBody List<Long> userIds);

    @CircuitBreaker(name = "USER-SERVICE", fallbackMethod = "fallbackGetUserById")
    @GetMapping("/api/v1/users/{userId}")
    UserResponse getUserById(@PathVariable("userId") Long userId, @RequestParam("short") Boolean isShort);

    default List<UserResponse> fallbackGetUsersByUserIds(Throwable throwable) {
        return List.of();
    }

    default UserResponse fallbackGetUserById(Throwable throwable) {
        return UserResponse.builder().build();
    }



}
