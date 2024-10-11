package org.bitmonsters.commentservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bitmonsters.commentservice.dto.CommentReactDto;
import org.bitmonsters.commentservice.dto.CommentReactionStatDto;
import org.bitmonsters.commentservice.dto.NewCommentReactDto;
import org.bitmonsters.commentservice.service.CommentReactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/comments/{commentId}/reactions")
@RequiredArgsConstructor
public class ReactionController {

    private final CommentReactionService reactionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void reactToComment(
            @PathVariable("commentId") Long commentId,
            @RequestHeader("userId") Long userId,
            @Valid  @RequestBody NewCommentReactDto newCommentReactDto
    ) {
        reactionService.reactToComment(commentId, userId, newCommentReactDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeReact(
            @PathVariable("commentId") Long commentId,
            @RequestHeader("userId") Long userId
    ) {
        reactionService.removeReact(commentId, userId);
    }

    @GetMapping
    public Page<CommentReactDto> getReactsToComment(
            @PathVariable("commentId") Long commentId,
            Pageable page
    ) {
        return reactionService.getReactsToComment(commentId, page);
    }

    @GetMapping("/me")
    public Boolean isReactToComment(
            @PathVariable("commentId") Long commentId,
            @RequestHeader("userId") Long userId
    ) {
        return reactionService.isReactToComment(commentId, userId);
    }

    @PutMapping
    public void updateCommentReact(
            @PathVariable("commentId") Long commentId,
            @RequestHeader("userId") Long userId,
            @Valid @RequestBody NewCommentReactDto newCommentReactDto
    ) {
        reactionService.updateCommentReact(commentId, userId, newCommentReactDto);
    }

    @GetMapping("/count")
    public Long getCommentReactionCount(
            @PathVariable("commentId") Long commentId
    ) {
        return reactionService.getReactionCountOfComment(commentId);
    }

    @GetMapping("/stat")
    public CommentReactionStatDto getCommentReactionStat(
            @PathVariable("commentId") Long commentId
    ) {
        return reactionService.getCommentReactionStats(commentId);
    }


}
