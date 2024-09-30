package org.bitmonsters.pollservice.service;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.pollservice.dto.PollStatByDateDto;
import org.bitmonsters.pollservice.dto.PollStatResultsDto;
import org.bitmonsters.pollservice.exception.PollException;
import org.bitmonsters.pollservice.model.AnswerStatus;
import org.bitmonsters.pollservice.repository.PollAnswerRepository;
import org.bitmonsters.pollservice.repository.PollAttemptsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PollStatService {

    private final PollAttemptsRepository pollAttemptsRepository;
    private final PollAnswerRepository pollAnswerRepository;
    private final PollService pollService;

    public PollStatResultsDto getPollAttemptsStatsByAnswerId(Long pollId, Long userId) {
        var poll = pollService.findPoll(pollId, false, false, 0L);

        if (!poll.getUserId().equals(userId) && !pollService.isAnsweredBefore(pollId, userId)) {
            throw new PollException(String.format("user with id %d cannot access the poll stat of id %d", userId, pollId));
        }

        // now get the answers of the given poll
        var answers = pollAnswerRepository.findAllByPollId(pollId);
        List<PollStatResultsDto.PollAnswer> answerStats = new ArrayList<>(answers.size());
        Long totalCount = 0L;
        Long totalRightCount = 0L;
        Long totalWrongCount = 0L;

        for (var answer: answers) {
            // get the vote count on this answer
            Long count = pollAttemptsRepository.countDistinctByPollIdAndAnswerId(pollId, answer.getAnswerId());
            // add to the answer stats
            answerStats.add(
                    PollStatResultsDto.PollAnswer.builder()
                            .answerId(answer.getAnswerId())
                            .answer(answer.getAnswer())
                            .status(answer.getAnswerStatus())
                            .count(count)
                            .build()
            );

            totalCount += count;
            if (answer.getAnswerStatus() == AnswerStatus.RIGHT)
                totalRightCount += count;
            else if (answer.getAnswerStatus() == AnswerStatus.WRONG)
                totalWrongCount += count;
        }

        // get the number of optional answers on the poll
        Long optionalAnswerCount = pollAttemptsRepository.countDistinctByPollIdAndOptionalAnswer(pollId, null);

        return PollStatResultsDto.builder()
                .answerCounts(answerStats)
                .totalCount(totalCount)
                .totalRightAnswers(totalRightCount)
                .totalWrongAnswers(totalWrongCount)
                .optionalAnswerCount(totalCount - optionalAnswerCount)
                .build();
    }

    public PollStatByDateDto getPollAttemptsStatByDate(Long pollId, Long userId, LocalDate startDate, LocalDate endDate) {
        var poll = pollService.findPoll(pollId, true, false, userId);

        // extract the poll data by their voted date
        // determine the time interval of attempt results
        if (startDate == null)
            startDate = poll.getCreatedAt().toLocalDate();
        if (endDate == null)
            endDate = LocalDate.now();



        // get poll stat instance
        var pollStat = new PollStatByDateDto();
        // set the start and end date
        pollStat.setStartDate(startDate);
        pollStat.setEndDate(endDate);

        // get the answer record from the answer repository
        var answers = pollAnswerRepository.findAllByPollId(pollId);
        // add the answer records to the poll stat instance
        answers.forEach(pollStat::addAnswerRecord);

        // now iterate through the until current date equal to end date
        while (!pollStat.isEnd()) {
            Map<Integer ,Long> attempts = new HashMap<>(answers.size());
            // get the current date and query the repository for
            answers.forEach(
                    answer -> {
                        // query the repository
                        Long count = pollAttemptsRepository.countAllByPollIdAndAnswerIdAndAnsweredAtAfterAndAnsweredAtBefore(
                                pollId,
                                answer.getAnswerId(),
                                pollStat.getCurrentDate().atStartOfDay(),
                                pollStat.getCurrentDate().plusDays(1).atStartOfDay()
                        );
                        // add to the map
                        attempts.put(answer.getAnswerId(), count);
                    }
            );

            // find the optional answer count
            Long optionalAnswerCount = pollAttemptsRepository.countAllByPollIdAndAnsweredAtAfterAndAnsweredAtBeforeAndOptionalAnswer(
                    pollId,
                    pollStat.getCurrentDate().atStartOfDay(),
                    pollStat.getCurrentDate().plusDays(1).atStartOfDay(),
                    null
            );

            // add the date record to the poll stat instance
            pollStat.addAttemptsToCurrentDate(attempts, optionalAnswerCount);
        }

        return pollStat;
    }
}
