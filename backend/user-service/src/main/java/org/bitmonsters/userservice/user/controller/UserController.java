package org.bitmonsters.userservice.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bitmonsters.userservice.dto.IdResponse;
import org.bitmonsters.userservice.user.dto.UserRegisterDto;
import org.bitmonsters.userservice.user.dto.UserResponse;
import org.bitmonsters.userservice.user.service.UserService;
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
    public ResponseEntity<IdResponse> registerUser(
            @Validated @RequestBody UserRegisterDto userRegisterDto
    ) {
        IdResponse response = service.createUser(userRegisterDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public Page<UserResponse> getUsers(Pageable page) {
        return service.getUsers(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(
            @PathVariable("id") Long userId,
            @RequestParam("short") Boolean isShort
    ) {
        return service.getUser(userId, isShort);
    }
}
