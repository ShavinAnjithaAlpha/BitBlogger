package org.bitmonsters.userservice.me;

import jakarta.transaction.Transactional;
import org.bitmonsters.userservice.user.Follower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowerRepository extends JpaRepository<Follower, Long> {

    @Transactional
    void deleteByFollowerIdAndFollowingId(Long followerId, Long followingId);

}
