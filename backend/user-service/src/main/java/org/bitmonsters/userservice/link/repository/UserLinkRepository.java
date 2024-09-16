package org.bitmonsters.userservice.link.repository;

import org.bitmonsters.userservice.link.model.UserLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLinkRepository extends JpaRepository<UserLink, Long> {

    Optional<UserLink> findByUserIdAndPlatformId(Long userId, Integer platformId);

    long countByUserId(Long userId);
}
