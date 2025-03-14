package org.bitmonsters.userservice.me.service;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.userservice.exception.FollowException;
import org.bitmonsters.userservice.exception.UserNotFoundException;
import org.bitmonsters.userservice.me.repository.FollowerRepository;
import org.bitmonsters.userservice.user.model.User;
import org.bitmonsters.userservice.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowerService {

    private final FollowerRepository repository;
    private final FollowMapper mapper;
    private final UserRepository userRepository;

    public void createFollower(Long userId, Long followerId) {
        // check whether if the follower exists
        if (!isUserExists(followerId)) {
            throw new UserNotFoundException(String.format("follower with user id %d not found", followerId));
        }

        if (userId.equals(followerId)) {
            throw new FollowException("cannot follow yourselves");
        }

        if (followerExists(userId, followerId)) {
            throw new FollowException(String.format("follow user with id %d already", followerId));
        }

        repository.save(mapper.toFollow(userId, followerId));
    }

    private boolean followerExists(Long userId, Long followerId) {
        var follow = repository.findByFollowerIdAndFollowingId(userId, followerId);
        return follow.isPresent();
    }

    private boolean isUserExists(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.isPresent();
    }

    public void unfollowUser(Long userId, Long followerId) {
        repository.deleteByFollowerIdAndFollowingId(userId, followerId);
    }


    public Page<?> getFollowedUsers(Long userId, Pageable page, Boolean detailed) {
        var followers = repository.findAllByFollower(
                User.builder().id(userId).build(), page
        );

        if (detailed) return followers.map(mapper::toDetailedFollowerResponse);
        return followers.map(mapper::toFollowerResponse);
    }

    public Page<?> getFollowingUsers(Long userId, Pageable page, Boolean detailed) {
        var followings =  repository.findAllByFollowing(
                User.builder().id(userId).build(), page
        );

        if (detailed) return followings.map(mapper::toDetailedFollowingResponse);
        return followings.map(mapper::toFollowingResponse);
    }
}
