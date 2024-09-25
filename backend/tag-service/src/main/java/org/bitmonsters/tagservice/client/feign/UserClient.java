package org.bitmonsters.tagservice.client.feign;

import org.bitmonsters.tagservice.client.fallback.UserClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "USER-SERVICE", fallbackFactory = UserClientFallbackFactory.class)
public interface UserClient {

    @GetMapping("/api/v1/users/{userId}")
    UserResponse getUserByID(@PathVariable("userId") Long userId, @RequestParam("short") Boolean isShort);

}
