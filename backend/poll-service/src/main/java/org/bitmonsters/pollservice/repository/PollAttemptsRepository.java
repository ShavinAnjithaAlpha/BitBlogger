package org.bitmonsters.pollservice.repository;

import org.bitmonsters.pollservice.model.PollAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PollAttemptsRepository extends JpaRepository<PollAttempt, Long> {
    List<PollAttempt> findAllByPollIdAndUserId(Long pollId, Long userId);
}
