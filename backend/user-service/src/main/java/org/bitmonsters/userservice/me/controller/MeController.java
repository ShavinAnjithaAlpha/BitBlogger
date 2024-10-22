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
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/me")
@RequiredArgsConstructor
public class MeController {

    private final UserService userService;
    private final FollowerService followerService;

    @GetMapping
    public UserResponse getAuthenticatedUser(Authentication authentication) {
        return userService.getUser((Long) authentication.getPrincipal());
    }

    @PutMapping
    public ResponseEntity<MessageResponse> updateAuthenticatedUser(Authentication authentication,
                                                                   @Validated @RequestBody UserUpdateDto userUpdateDto) {
        userService.updateUser((Long) authentication.getPrincipal(), userUpdateDto);
        return ResponseEntity.ok(new MessageResponse("profile updated successfully"));
    }

    @DeleteMapping
    public ResponseEntity<MessageResponse> deleteAuthenticatedUSer(Authentication authentication) {
        userService.deleteUser((Long) authentication.getPrincipal());
        return ResponseEntity.ok(new MessageResponse("user deleted successfully"));
    }

    @PostMapping("/follow")
    public ResponseEntity<MessageResponse> followUser(Authentication authentication,
                                             @RequestBody UserFollowDto userFollowDto) {
        followerService.createFollower((Long) authentication.getPrincipal(), userFollowDto.followerId());
        return ResponseEntity.ok(new MessageResponse("follower added successfully"));
    }

    @DeleteMapping("/follow")
    public ResponseEntity<MessageResponse> unfollowUser(Authentication authentication,
                                               @RequestBody UserFollowDto userFollowDto) {
        followerService.unfollowUser((Long) authentication.getPrincipal(), userFollowDto.followerId());
        return ResponseEntity.ok(new MessageResponse("unfollow the user successfully"));
    }

    @GetMapping("/follow")
    public Page<?> getFollowedUsers(
            Authentication authentication,
            @RequestParam(name = "detailed", defaultValue = "0") boolean detailed,
            Pageable page) {
        return followerService.getFollowedUsers((Long) authentication.getPrincipal(), page, detailed);
    }

    @GetMapping("/followings")
    public Page<?> getFollowingUsers(
            Authentication authentication,
            @RequestParam(name = "detailed", defaultValue = "0") Boolean detailed,
            Pageable page) {
        return followerService.getFollowingUsers((Long) authentication.getPrincipal(), page, detailed);
    }

    @PutMapping("/email/display")
    public MessageResponse setEmailAsPublic(
            Authentication authentication
    ) {
        userService.makeEmailPublic((Long) authentication.getPrincipal());
        return new MessageResponse("email set as public");
    }

    @PutMapping("/email/hide")
    public MessageResponse setEmailAsPrivate(
            Authentication authentication
    ) {
        userService.makeEmailPrivate((Long) authentication.getPrincipal());
        return new MessageResponse("email set as private");
    }

    @PostMapping("/reports")
    public MessageResponse reportUser(
            Authentication authentication,
            @Validated @RequestBody UserReportDto reportDto
    ) {
        userService.reportUser((Long) authentication.getPrincipal(), reportDto);
        return new MessageResponse(String.format("user with id %d reported successfully", reportDto.userId()));
    }


}
