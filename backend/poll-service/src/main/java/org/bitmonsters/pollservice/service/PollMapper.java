package org.bitmonsters.pollservice.service;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.pollservice.client.feign.TagClient;
import org.bitmonsters.pollservice.client.feign.TagResponse;
import org.bitmonsters.pollservice.dto.*;
import org.bitmonsters.pollservice.model.*;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PollMapper {

    private final TagClient tagClient;

    public Poll toPoll(NewPollDto newPollDto, Long userId) {
        return Poll.builder()
                .question(newPollDto.question())
                .photo(newPollDto.photo())
                .endsAt(toLocalDateTimeFromNow(newPollDto.duration()))
                .userId(userId)
                .type(newPollDto.pollType())
                .metaData(toMetaData(newPollDto))
                .build();
    }

    private PollMetaData toMetaData(NewPollDto newPollDto) {
        return PollMetaData.builder()
                .hasAnswer(newPollDto.hasAnswer() != null ? newPollDto.hasAnswer() : Boolean.FALSE)
                .optionalAnswerAllowed(newPollDto.optionalAnswerAllowed() != null ? newPollDto.optionalAnswerAllowed() : Boolean.FALSE)
                .voteVisibility(newPollDto.voteVisibility() != null ? newPollDto.voteVisibility() : Boolean.FALSE)
                .answerVisibility(newPollDto.answerVisibility() != null ? newPollDto.answerVisibility() : Boolean.FALSE)
                .build();
    }

    private LocalDateTime toLocalDateTimeFromNow(DurationDto duration) {
        return LocalDateTime.now()
                .plusDays(duration.getDays())
                .plusHours(duration.getHours())
                .plusMinutes(duration.getMinutes())
                .plusSeconds(duration.getSeconds());
    }

    public PollTag toTag(Integer tag, Poll poll) {
        return PollTag.builder()
                .poll(poll)
                .tagId(tag)
                .build();
    }

    public PollAnswer toAnswer(PollAnswerDto answer, Poll poll, Integer answerId) {
        return PollAnswer.builder()
                .answerId(answerId)
                .answer(answer.answer())
                .answerStatus(answer.status())
                .poll(poll)
                .build();
    }

    public PollDto toPollDto(Poll poll) {
        return PollDto.builder()
                .id(poll.getId())
                .question(poll.getQuestion())
                .photo(poll.getPhoto())
                .type(poll.getType())
                .hasAnswer(poll.getMetaData().getHasAnswer())
                .optionalAnswerAllowed(poll.getMetaData().getOptionalAnswerAllowed())
                .expired(poll.getEndsAt().isBefore(LocalDateTime.now()))
                .remaining(poll.getEndsAt().isBefore(LocalDateTime.now())
                                ? DurationDto.fromDuration(Duration.ZERO) :
                                DurationDto.fromDuration(Duration.between(LocalDateTime.now(), poll.getEndsAt())))
                .tags(poll.getTags().stream()
                        .map(tag -> toTag(tagClient.getTag(tag.getTagId())))
                        .collect(Collectors.toList())
                )
                .answers(poll.getAnswers().stream()
                        .map(this::toPollDtoAnswer)
                        .collect(Collectors.toList()))
                .build();
    }

    private PollDto.PollAnswer toPollDtoAnswer(PollAnswer pollAnswer) {
        return PollDto.PollAnswer.builder()
                .answerId(pollAnswer.getAnswerId())
                .answer(pollAnswer.getAnswer())
                .build();
    }

    private Tag toTag(TagResponse tag) {
        return Tag.builder()
                .name(tag.name())
                .id(tag.id())
                .build();
    }

    public PollAttempt toPollAttempt(Integer answerId, Poll poll, Long userId, Boolean isPublic) {
        return PollAttempt.builder()
                .answerId(answerId)
                .optionalAnswer(null)
                .poll(poll)
                .userId(userId)
                .isPublic(isPublic != null ? isPublic : Boolean.TRUE)
                .build();
    }

    public PollAttempt toPollAttempt(String optionalAnswer, Poll poll, Long userId, Boolean isPublic) {
        return PollAttempt.builder()
                .answerId(null)
                .optionalAnswer(optionalAnswer)
                .poll(poll)
                .userId(userId)
                .isPublic(isPublic != null ? isPublic : Boolean.TRUE)
                .build();
    }


}
