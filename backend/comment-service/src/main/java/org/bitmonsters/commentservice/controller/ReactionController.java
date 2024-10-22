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
import org.springframework.security.core.Authentication;
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
            Authentication authentication,
            @Valid  @RequestBody NewCommentReactDto newCommentReactDto
    ) {
        reactionService.reactToComment(commentId, (Long) authentication.getPrincipal(), newCommentReactDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeReact(
            @PathVariable("commentId") Long commentId,
            Authentication authentication
    ) {
        reactionService.removeReact(commentId, (Long) authentication.getPrincipal());
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
            Authentication authentication
    ) {
        return reactionService.isReactToComment(commentId, (Long) authentication.getPrincipal());
    }

    @PutMapping
    public void updateCommentReact(
            @PathVariable("commentId") Long commentId,
            Authentication authentication,
            @Valid @RequestBody NewCommentReactDto newCommentReactDto
    ) {
        reactionService.updateCommentReact(commentId, (Long) authentication.getPrincipal(), newCommentReactDto);
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
