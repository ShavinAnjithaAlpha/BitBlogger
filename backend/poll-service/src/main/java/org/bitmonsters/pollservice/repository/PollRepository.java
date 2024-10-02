package org.bitmonsters.pollservice.repository;

import org.bitmonsters.pollservice.dto.PollDto;
import org.bitmonsters.pollservice.model.Poll;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {
    Slice<Poll> findAllByUserId(Long userId, Pageable page);
}
