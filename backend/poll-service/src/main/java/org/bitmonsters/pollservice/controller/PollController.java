package org.bitmonsters.pollservice.controller;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.pollservice.dto.*;
import org.bitmonsters.pollservice.service.PollService;
import org.bitmonsters.pollservice.service.PollStatService;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/polls")
@RequiredArgsConstructor
public class PollController {

    private final PollService service;
    private final PollStatService pollStatService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IdResponse addPoll(
            @Validated @RequestBody NewPollDto newPollDto,
            @RequestHeader("userId") Long userId
    ) {
        return service.addPoll(newPollDto, userId);
    }

    @PutMapping("/{pollId}")
    public void updatePoll(
            @PathVariable("pollId") Long pollId,
            @Validated @RequestBody PollUpdateDto pollUpdateDto,
            @RequestHeader("userId") Long userId
    ) {
        service.updatePoll(pollId, pollUpdateDto, userId);
    }

    @DeleteMapping("/{pollId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePoll(
            @PathVariable("pollId") Long pollId,
            @RequestHeader("userId") Long userId
    ) {
        service.deletePoll(pollId, userId);
    }

    @PostMapping("/{pollId}/tags/{tagId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTagToPoll(
            @PathVariable("pollId") Long pollId,
            @PathVariable("tagId") Integer tagId,
            @RequestHeader("userId") Long userId
    ) {
        service.addTagToPoll(pollId, tagId, userId);
    }

    @GetMapping
    public Page<PollDto> getPolls(Pageable page) {
        return service.getPolls(page);
    }

    @GetMapping("/{pollId}")
    public PollDto getPoll(
            @PathVariable("pollId") Long pollId
    ) {
        return service.getPoll(pollId);
    }

    @GetMapping("/users/{userId}")
    public Slice<PollDto> getPollOfUser(
            @PathVariable("userId") Long userId,
            Pageable page
    ) {
        return service.getPollOfUser(userId, page);
    }

    @GetMapping("/users/me")
    public Slice<PollDto> getPollOfAuthenticatedUser(
            @RequestHeader("userId") Long userId,
            Pageable page
    ) {
        return service.getPollOfUser(userId, page);
    }

    @PostMapping("/{pollId}/answer")
    @ResponseStatus(HttpStatus.CREATED)
    public void answerPoll(
            @PathVariable("pollId") Long pollId,
            @RequestHeader("userId") Long userId,
            @Validated @RequestBody NewPollAnswerDto newPollAnswerDto
    ) {
        service.answerPoll(pollId, userId, newPollAnswerDto);
    }

    @GetMapping("/{pollId}/answer/status")
    public PollStatusDto getStatusOfPollAnswer(
            @PathVariable("pollId") Long pollId,
            @RequestHeader("userId") Long userId
    ) {
        return service.getStatusOfPollAnswer(pollId, userId);
    }

    @GetMapping("/{pollId}/results")
    public Slice<PollAttemptDto> getPollAttempts(
            @PathVariable("pollId") Long pollId,
            @RequestHeader("userId") Long userId,
            Pageable page
    ) {
        return service.getPollAttempts(pollId, userId, page);
    }

    @GetMapping("/{pollId}/results/stat")
    public ResponseEntity<?> getPollResultsStats(
            @PathVariable("pollId") Long pollId,
            @RequestHeader("userId") Long userId,
            @RequestParam(name = "dateWise", defaultValue = "0") Boolean dateWise,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate endDate
            ) {
        if (dateWise) {
            return ResponseEntity.ok(pollStatService.getPollAttemptsStatByDate(pollId, userId, startDate, endDate));
        } else {
            return ResponseEntity.ok(pollStatService.getPollAttemptsStatsByAnswerId(pollId, userId));
        }
    }

}
