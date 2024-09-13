package org.bitmonsters.userservice.reading;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReadingRepository extends JpaRepository<ReadingList, Long> {

    Optional<ReadingList> findByUserIdAndPostId(Long userId, Long postId);

    Page<ReadingList> findByUserId(Long userId, Pageable page);
}
