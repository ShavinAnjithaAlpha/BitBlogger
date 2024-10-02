package org.bitmonsters.userservice.user.controller;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.userservice.user.dto.ShortUserResponse;
import org.bitmonsters.userservice.user.service.UserBatchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/batch")
@RequiredArgsConstructor
public class UserBatchController {

    private final UserBatchService service;

    @PostMapping
    public List<ShortUserResponse> getUserBatch(
            @RequestBody List<Long> userIds
    ) {
        return service.getUserBatch(userIds);
    }

}
