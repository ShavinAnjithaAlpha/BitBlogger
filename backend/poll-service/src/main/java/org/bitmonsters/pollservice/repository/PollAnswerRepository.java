package org.bitmonsters.pollservice.repository;

import org.bitmonsters.pollservice.model.PollAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PollAnswerRepository extends JpaRepository<PollAnswer, Long> {
    Optional<PollAnswer> findByPollIdAndAnswerId(Long pollId, Integer answerId);

    List<PollAnswer> findAllByPollId(Long pollId);
}
