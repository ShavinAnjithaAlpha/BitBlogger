package org.bitmonsters.userservice.me;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.userservice.user.UserResponse;
import org.bitmonsters.userservice.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> updateAuthenticatedUser(@RequestHeader(name = "userId") Long userId,
                                                          @RequestBody UserUpdateDto userUpdateDto) {
        userService.updateUser(userUpdateDto);
        return ResponseEntity.ok("profile updated successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAuthenticatedUSer(@RequestHeader(name = "userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("user deleted successfully");
    }

    @PostMapping("/follow")
    public ResponseEntity<String> followUser(@RequestHeader(name = "userId") Long userId,
                                             @RequestBody UserFollowDto userFollowDto) {
        followerService.createFollower(userId, userFollowDto.followerId());
        return ResponseEntity.ok("follower added successfully");
    }

    @DeleteMapping("/follow")
    public ResponseEntity<String> unfollowUser(@RequestHeader(name = "userId") Long userId,
                                               @RequestBody UserFollowDto userFollowDto) {
        followerService.unfollowUser(userId, userFollowDto.followerId());
        return ResponseEntity.ok("unfollow the user successfully");
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




}
