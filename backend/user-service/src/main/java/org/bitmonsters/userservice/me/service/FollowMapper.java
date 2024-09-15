package org.bitmonsters.userservice.me.service;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.userservice.me.dto.FollowerResponse;
import org.bitmonsters.userservice.user.dto.UserResponse;
import org.bitmonsters.userservice.user.model.Follower;
import org.bitmonsters.userservice.user.model.User;
import org.bitmonsters.userservice.user.service.UserMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowMapper {

    private final UserMapper mapper;

    public Follower toFollow(Long userId, Long followerId) {
        return Follower.builder()
                .follower(
                        User.builder().id(userId).build()
                )
                .following(User.builder().id(followerId).build())
                .build();
    }

    public FollowerResponse toFollowerResponse(Follower follower) {
        return FollowerResponse.builder()
                .id(follower.getFollowing().getId())
                .username(follower.getFollowing().getUsername())
                .name(follower.getFollowing().getName())
                .email(follower.getFollowing().getEmail())
                .profileImage(follower.getFollowing().getProfileImage())
                .websiteUrl(follower.getFollowing().getWebsiteUrl())
                .followedAt(follower.getCreatedAt())
                .build();
    }

    public FollowerResponse toFollowingResponse(Follower follower) {
        return FollowerResponse.builder()
                .id(follower.getFollower().getId())
                .username(follower.getFollower().getUsername())
                .name(follower.getFollower().getName())
                .email(follower.getFollower().getEmail())
                .profileImage(follower.getFollower().getProfileImage())
                .websiteUrl(follower.getFollower().getWebsiteUrl())
                .followedAt(follower.getCreatedAt())
                .build();
    }

    public UserResponse toDetailedFollowerResponse(Follower follower) {
        return mapper.toUserResponse(follower.getFollowing());
    }

    public UserResponse toDetailedFollowingResponse(Follower follower) {
        return mapper.toUserResponse(follower.getFollower());
    }
}
