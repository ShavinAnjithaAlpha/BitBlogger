package org.bitmonsters.tagservice.client.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.bitmonsters.tagservice.client.config.FeignUserClientConfig;
import org.bitmonsters.tagservice.client.fallback.UserClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "USER-SERVICE", fallbackFactory = UserClientFallbackFactory.class, configuration = FeignUserClientConfig.class)
public interface UserClient {

    @CircuitBreaker(name = "USER-SERVICE", fallbackMethod = "fallbackGetUserByID")
    @GetMapping("/api/v1/users/{userId}")
    UserResponse getUserByID(@PathVariable("userId") Long userId, @RequestParam("short") Boolean isShort);

    default UserResponse fallbackGetUserByID(Throwable throwable) {
        return UserResponse.builder().build();
    }

}
