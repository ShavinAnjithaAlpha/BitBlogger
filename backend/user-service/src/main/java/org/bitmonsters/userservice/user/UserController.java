package org.bitmonsters.userservice.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Log4j2
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<Long> registerUser(
            @Validated @RequestBody UserRegisterDto userRegisterDto
    ) {
        Long userId = service.createUser(userRegisterDto);
        return ResponseEntity.ok(userId);
    }

    @GetMapping
    public Page<UserResponse> getUsers(Pageable page) {
        return service.getUsers(page);
    }

    @GetMapping("/{id}")
    public UserResponse getUser(
            @PathVariable("id") Long userId
    ) {
        return service.getUser(userId);
    }
}
