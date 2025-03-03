package org.bitmonsters.userservice.me.controller;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.userservice.dto.MessageResponse;
import org.bitmonsters.userservice.me.dto.UserFollowDto;
import org.bitmonsters.userservice.me.dto.UserReportDto;
import org.bitmonsters.userservice.me.dto.UserUpdateDto;
import org.bitmonsters.userservice.me.service.FollowerService;
import org.bitmonsters.userservice.user.dto.UserResponse;
import org.bitmonsters.userservice.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/me")
@RequiredArgsConstructor
public class MeController {

    private final UserService userService;
    private final FollowerService followerService;

    @GetMapping
    public UserResponse getAuthenticatedUser(@RequestHeader(name = "userId") Long userId) {
        return userService.getUser(userId);
    }

    @PutMapping
    public ResponseEntity<MessageResponse> updateAuthenticatedUser(@RequestHeader(name = "userId") Long userId,
                                                                   @Validated @RequestBody UserUpdateDto userUpdateDto) {
        userService.updateUser(userId, userUpdateDto);
        return ResponseEntity.ok(new MessageResponse("profile updated successfully"));
    }

    @DeleteMapping
    public ResponseEntity<MessageResponse> deleteAuthenticatedUSer(@RequestHeader(name = "userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok(new MessageResponse("user deleted successfully"));
    }

    @PostMapping("/follow")
    public ResponseEntity<MessageResponse> followUser(@RequestHeader(name = "userId") Long userId,
                                             @RequestBody UserFollowDto userFollowDto) {
        followerService.createFollower(userId, userFollowDto.followerId());
        return ResponseEntity.ok(new MessageResponse("follower added successfully"));
    }

    @DeleteMapping("/follow")
    public ResponseEntity<MessageResponse> unfollowUser(@RequestHeader(name = "userId") Long userId,
                                               @RequestBody UserFollowDto userFollowDto) {
        followerService.unfollowUser(userId, userFollowDto.followerId());
        return ResponseEntity.ok(new MessageResponse("unfollow the user successfully"));
    }

    @GetMapping("/follow")
    public Page<?> getFollowedUsers(
            @RequestHeader(name = "userId") Long userId,
            @RequestParam(name = "detailed", defaultValue = "0") boolean detailed,
            Pageable page) {
        return followerService.getFollowedUsers(userId, page, detailed);
    }

    @GetMapping("/followings")
    public Page<?> getFollowingUsers(
            @RequestHeader(name = "userId") Long userId,
            @RequestParam(name = "detailed", defaultValue = "0") Boolean detailed,
            Pageable page) {
        return followerService.getFollowingUsers(userId, page, detailed);
    }

    @PutMapping("/email/display")
    public MessageResponse setEmailAsPublic(
            @RequestHeader("userId") Long userId
    ) {
        userService.makeEmailPublic(userId);
        return new MessageResponse("email set as public");
    }

    @PutMapping("/email/hide")
    public MessageResponse setEmailAsPrivate(
            @RequestHeader("userId") Long userId
    ) {
        userService.makeEmailPrivate(userId);
        return new MessageResponse("email set as private");
    }

    @PostMapping("/reports")
    public MessageResponse reportUser(
            @RequestHeader("userId") Long userId,
            @Validated @RequestBody UserReportDto reportDto
    ) {
        userService.reportUser(userId, reportDto);
        return new MessageResponse(String.format("user with id %d reported successfully", reportDto.userId()));
    }


}
