package org.bitmonsters.pollservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bitmonsters.pollservice.client.feign.TagClient;
import org.bitmonsters.pollservice.client.feign.UserClient;
import org.bitmonsters.pollservice.client.feign.UserResponse;
import org.bitmonsters.pollservice.dto.*;
import org.bitmonsters.pollservice.exception.*;
import org.bitmonsters.pollservice.model.AnswerStatus;
import org.bitmonsters.pollservice.model.Poll;
import org.bitmonsters.pollservice.model.PollType;
import org.bitmonsters.pollservice.repository.PollAnswerRepository;
import org.bitmonsters.pollservice.repository.PollAttemptsRepository;
import org.bitmonsters.pollservice.repository.PollRepository;
import org.bitmonsters.pollservice.repository.PollTagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PollService {

    private static final Logger log = LoggerFactory.getLogger(PollService.class);
    private final PollRepository pollRepository;
    private final PollAttemptsRepository pollAttemptsRepository;
    private final PollTagRepository pollTagRepository;
    private final PollAnswerRepository pollAnswerRepository;
    private final PollMapper mapper;

    private final TagClient tagClient;
    private final UserClient userClient;

    @Transactional(value = Transactional.TxType.REQUIRED)
    public IdResponse addPoll(NewPollDto newPollDto, Long userId) {
        // create a new poll in the user's namespace
        var poll = pollRepository.save(mapper.toPoll(newPollDto, userId));

        if (newPollDto.tags() != null) {
            // add the tag related to poll
            for (var tag: newPollDto.tags()) {

                if (!tagExists(tag))
                    throw new TagNotFoundException(String.format("tag with id %d is not found", tag));

                pollTagRepository.save(mapper.toTag(tag, poll));
            }
        }

        // check whether there is at least one right answer if the poll have an answer and vice versa
        int count = 0;
        for (var answer: newPollDto.answers()) {
            if (answer.status() == AnswerStatus.RIGHT)
                count++;
        }

        if (newPollDto.hasAnswer() && count == 0)
            throw new PollFormatException("poll with answer mst have at least one right answer");

        if (!newPollDto.hasAnswer() && count > 0)
            throw new PollFormatException("poll with no answer cannot have a right answer");

        // store the answer associated with the poll
        for (var answer: newPollDto.answers()) {
            pollAnswerRepository.save(mapper.toAnswer(answer, poll, newPollDto.answers().indexOf(answer)));
        }

        return new IdResponse(poll.getId());
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void updatePoll(Long pollId, PollUpdateDto pollUpdateDto, Long userId) {
        // get the poll
        var poll = findPoll(pollId, true, true, userId);

        // update the certain fields
        if (pollUpdateDto.timeAdded() != null)
            poll.setEndsAt(poll.getEndsAt().plus(pollUpdateDto.timeAdded().toDuration()));
        // check whether if the ends time go under the current time
        if (pollUpdateDto.timeRemoved() != null && poll.getEndsAt().minus(pollUpdateDto.timeRemoved().toDuration()).isAfter(LocalDateTime.now())) {
            throw new PollFormatException(String.format("poll ends at cannot be in past: current time: %s, ends time: %s",
                    LocalDateTime.now(), poll.getEndsAt().minus(pollUpdateDto.timeRemoved().toDuration())));
        }

        // update the end at field of the poll
        if (pollUpdateDto.timeRemoved() != null) {
            poll.setEndsAt(poll.getEndsAt().minus(pollUpdateDto.timeRemoved().toDuration()));
        }

        // set other fields
        poll.getMetaData().setAnswerVisibility(pollUpdateDto.answerVisibility());
        poll.getMetaData().setVoteVisibility(pollUpdateDto.voteVisibility());

        // save the poll
        pollRepository.save(poll);
    }

    @Transactional
    public void deletePoll(Long pollId, Long userId) {
        var poll = pollRepository.findById(pollId).orElse(null);
        if (poll != null) {
            if (!Objects.equals(poll.getUserId(), userId))
                throw new PollNotFoundException(String.format("poll with id %d is not found", pollId));

            // otherwise delete the poll
            pollRepository.delete(poll);
        }
    }

    @Transactional
    public void addTagToPoll(Long pollId, Integer tagId, Long userId) {
        var poll = findPoll(pollId, true, true, userId);

        if (!tagExists(tagId))
            throw new TagNotFoundException(String.format("tag with id %d is not found", tagId));

        // add the tag to the poll
        pollTagRepository.save(mapper.toTag(tagId, poll));
    }

    private boolean tagExists(Integer tagId) {
        try {
            // check whether if the tag exists // external call via HTTP for tag service
            var _tag = tagClient.getTag(tagId);
            return _tag != null;
        } catch (TagNotFoundException exp) {
            return false;
        }
    }

    public Poll findPoll(Long pollId, boolean ownershipCheck, boolean expirationCheck, Long userId) {
        var poll = pollRepository.findById(pollId).orElseThrow(
                () -> new PollNotFoundException(String.format("poll with id %d is not found", pollId))
        );

        // check whether user is really the owner of the poll
        if (ownershipCheck && !Objects.equals(poll.getUserId(), userId))
            throw new PollNotFoundException(String.format("poll with id %d is not found", pollId));

        // check if the poll expired or not
        if (expirationCheck && poll.getEndsAt().isBefore(LocalDateTime.now()))
            throw new PollException(String.format("cannot update the poll with id %d: expired in %s",
                    poll.getId(), poll.getEndsAt()));

        return poll;
    }

    public Page<PollDto> getPolls(Pageable page) {
        return pollRepository.findAll(page).map(
                mapper::toPollDto);
    }

    public PollDto getPoll(Long pollId) {
        var poll = findPoll(pollId, false, false, 0L);

        return mapper.toPollDto(poll);
    }

    public Slice<PollDto> getPollOfUser(Long userId, Pageable page) {
        return pollRepository.findAllByUserId(userId, page).map(mapper::toPollDto);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void answerPoll(Long pollId, Long userId, NewPollAnswerDto newPollAnswerDto) {
        var poll = findPoll(pollId, false, false, 0L);

        // check whether given user answer the poll before
        if (isAnsweredBefore(pollId, userId))
            throw new PollAnswerException(String.format("user with id %d answered the poll with id %d before", userId, pollId));

        // check whether multiple or single answer is allowed
        if (poll.getType() == PollType.SINGLE && newPollAnswerDto.answers().size() > 1)
            throw new PollAnswerException(String.format("required only one answer for single answer poll: provided %d",
                    newPollAnswerDto.answers().size()));

        if (!poll.getMetaData().getOptionalAnswerAllowed() && newPollAnswerDto.answers().isEmpty()) {
            throw new PollAnswerException("answer ids are required");
        }

        // save the answers in the system
        if (newPollAnswerDto.answers() != null) {
            for (var answer: newPollAnswerDto.answers()) {
                pollAttemptsRepository.save(mapper.toPollAttempt(answer, poll, userId, newPollAnswerDto.isPublic()));
            }
        }

        // if optional answers are allowed store the optional answer also
        if (poll.getMetaData().getOptionalAnswerAllowed() &&
                newPollAnswerDto.optionalAnswer() != null &&
                !newPollAnswerDto.optionalAnswer().isEmpty()) {
            pollAttemptsRepository.save(mapper.toPollAttempt(newPollAnswerDto.optionalAnswer(), poll, userId, newPollAnswerDto.isPublic()));
        }
    }

    public boolean isAnsweredBefore(Long pollId, Long userId) {
        return !pollAttemptsRepository.findAllByPollIdAndUserId(pollId, userId).isEmpty();
    }

    public PollStatusDto getStatusOfPollAnswer(Long pollId, Long userId) {
        // get the poll first
        var poll = findPoll(pollId, false, false, 0L);

        if (!isAnsweredBefore(pollId, userId))
            throw new PollAnswerException(String.format("user with id %d not answered the poll with id %d", userId, pollId));

        // get the answers status
        var attempts = pollAttemptsRepository.findAllByPollIdAndUserId(pollId, userId);
        List<PollStatusDto.PollAnswerStatusRecord> answers = new ArrayList<>(attempts.size());
        for (var attempt: attempts) {
            if (attempt.getAnswerId() == null) continue;
            // get the answer record from the poll answer store
            var answer = pollAnswerRepository.findByPollIdAndAnswerId(pollId, attempt.getAnswerId()).get();
            answers.add(
                    PollStatusDto.PollAnswerStatusRecord.builder()
                            .answerId(answer.getAnswerId())
                            .status(answer.getAnswerStatus())
                            .build()
            );

        }

        return new PollStatusDto(answers);
    }

    public List<PollAttemptDto> getPollAttempts(Long pollId, Long userId, Pageable page) {
        // get the poll
        var poll = findPoll(pollId, true, false, userId);

        // get the poll attempts using poll attempts repository
        var attempts = pollAttemptsRepository.findAllByPollId(pollId, page);

        // extract the user indexes from the attempts list
        List<Long> userIds = new ArrayList<>(attempts.getSize());
        for (var attempt: attempts) {
            userIds.add(attempt.getUserId());
        }



        Map<Long, PollAttemptDto> uniqueAttempts = new HashMap<>();
        for (var attempt: attempts) {
            if (uniqueAttempts.getOrDefault(attempt.getUserId(), null) == null) {
                var attemptDto = PollAttemptDto.builder()
                        .user(userClient.getUserById(attempt.getUserId(), true))
                        .answeredAt(attempt.getAnsweredAt())
                        .optionalAnswer(attempt.getOptionalAnswer())
                        .answerIds(new ArrayList<>())
                        .build();
                attemptDto.answerIds().add(attempt.getAnswerId());
                uniqueAttempts.put(attempt.getUserId(), attemptDto);
            } else {
                uniqueAttempts.get(attempt.getUserId()).answerIds().add(attempt.getAnswerId());
            }
        }

        // get the user responses using the user client
//        List<UserResponse> users = userClient.getUsersByUserIDs(userIds);
        // map user IDs into users and return the result
        return new ArrayList<>(uniqueAttempts.values());
    }
}
