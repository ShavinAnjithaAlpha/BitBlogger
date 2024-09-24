package org.bitmonsters.tagservice.client.feign;

import org.bitmonsters.tagservice.client.fallback.UserClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "USER-SERVICE", fallback = UserClientFallback.class)
public interface UserClient {

    @GetMapping("/api/v1/users/{userId}")
    UserResponse getUserByID(@PathVariable("userId") Long userId, @RequestParam("short") Boolean isShort);

}
