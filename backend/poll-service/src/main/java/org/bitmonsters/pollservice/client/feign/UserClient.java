package org.bitmonsters.pollservice.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "USER-SERVICE")
public interface UserClient {

    @GetMapping("/api/v1/users/{userId}")
    UserResponse getUserById(@PathVariable("userId") Long userId, @RequestParam("short") Boolean isShort);

    @PostMapping("/api/v1/users/batch")
    List<UserResponse> getUsersByUserIDs(@RequestBody List<Long> userIds);
}
