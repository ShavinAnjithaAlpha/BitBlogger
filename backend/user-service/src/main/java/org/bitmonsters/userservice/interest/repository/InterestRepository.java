package org.bitmonsters.userservice.interest.repository;

import jakarta.transaction.Transactional;
import org.bitmonsters.userservice.interest.model.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InterestRepository extends JpaRepository<Interest, Long> {

    Optional<Interest> findByUserIdAndTagId(Long userId, Integer tagId);

    @Transactional
    void deleteInterestByUserIdAndTagId(Long userId, Integer tagId);

    List<Interest> findAllByUserId(Long userId);
}
