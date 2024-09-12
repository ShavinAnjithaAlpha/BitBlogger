package org.bitmonsters.userservice.me;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.userservice.exception.UserNotFoundException;
import org.bitmonsters.userservice.user.Follower;
import org.bitmonsters.userservice.user.User;
import org.bitmonsters.userservice.user.UserRepository;
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

        repository.save(mapper.toFollow(userId, followerId));
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
