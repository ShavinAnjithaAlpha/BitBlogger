package org.bitmonsters.topicservice.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "USER-SERVICE")
public interface UserClient {

    @GetMapping("/api/v1/users/{userId}")
    UserResponse getUserById(@PathVariable("userId") Long userId, @RequestParam("short") Boolean isShort);

}
