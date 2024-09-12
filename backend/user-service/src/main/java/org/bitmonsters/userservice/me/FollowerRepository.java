package org.bitmonsters.userservice.me;

import jakarta.transaction.Transactional;
import org.bitmonsters.userservice.user.Follower;
import org.bitmonsters.userservice.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowerRepository extends JpaRepository<Follower, Long> {

    @Transactional
    void deleteByFollowerIdAndFollowingId(Long followerId, Long followingId);

    Page<Follower> findAllByFollower(User user, Pageable page);
    Page<Follower> findAllByFollowing(User user, Pageable page);

}
