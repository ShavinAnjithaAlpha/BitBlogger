package org.bitmonsters.pollservice.repository;

import org.bitmonsters.pollservice.model.PollAttempt;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PollAttemptsRepository extends JpaRepository<PollAttempt, Long> {
    List<PollAttempt> findAllByPollIdAndUserId(Long pollId, Long userId);

    Slice<PollAttempt> findAllByPollId(Long pollId, Pageable page);

    Long countDistinctByPollIdAndAnswerId(Long pollId, Integer answerId);

    Long countDistinctByPollIdAndOptionalAnswerNotNull(Long pollId);

    Long countAllByPollIdAndAnswerIdAndAnsweredAtAfterAndAnsweredAtBefore(Long pollId, Integer answerId, LocalDateTime startTime, LocalDateTime endTime);

    Long countAllByPollIdAndAnsweredAtAfterAndAnsweredAtBeforeAndOptionalAnswerNotNull(Long pollId, LocalDateTime startTime, LocalDateTime endTime);
}
