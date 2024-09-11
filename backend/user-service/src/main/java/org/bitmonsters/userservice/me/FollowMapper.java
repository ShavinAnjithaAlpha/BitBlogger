package org.bitmonsters.userservice.me;

import org.bitmonsters.userservice.user.Follower;
import org.bitmonsters.userservice.user.User;
import org.springframework.stereotype.Service;

@Service
public class FollowMapper {


    public Follower toFollow(Long userId, Long followerId) {
        return Follower.builder()
                .follower(
                        User.builder().id(userId).build()
                )
                .following(User.builder().id(followerId).build())
                .build();
    }
}
