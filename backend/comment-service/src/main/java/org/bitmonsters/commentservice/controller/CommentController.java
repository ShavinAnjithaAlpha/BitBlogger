package org.bitmonsters.commentservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bitmonsters.commentservice.dto.*;
import org.bitmonsters.commentservice.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CommentController {


    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public IDResponse addComment(
            @PathVariable("postId") String postId,
            @Valid @RequestBody NewCommentDto newCommentDto,
            Authentication authentication
    ) {
        return commentService.addComment(postId, (Long) authentication.getPrincipal(), newCommentDto);
    }

    @GetMapping("/posts/{postId}/comments")
    public Page<CommentDto> getCommentsOfPost(
            @PathVariable("postId") String postId,
            Pageable page
    ) {
        return commentService.getCommentOfPost(postId, page);
    }

    @GetMapping("/posts/{postId}/comments/count")
    public Long getCommentCountOfPost(
            @PathVariable("postId") String postId
    ) {
        return commentService.getCommentCountOfPost(postId);
    }

    @GetMapping("/comments/{commentId}")
    public CommentDto getCommentById(
            @PathVariable("commentId") Long commentId
    ) {
        return commentService.getCommentById(commentId);
    }

    @PutMapping("/comments/{commentId}")
    public void updateComment(
            @PathVariable("commentId") Long commentId,
            Authentication authentication,
            @Valid @RequestBody NewCommentDto newCommentDto
    ) {
        commentService.updateComment(commentId, (Long) authentication.getPrincipal(), newCommentDto);
    }

    @DeleteMapping("/comments/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(
            @PathVariable("commentId") Long commentId,
            Authentication authentication
    ) {
        commentService.deleteComment(commentId, (Long) authentication.getPrincipal());
    }

    @PostMapping("/comments/{commentId}/replies")
    @ResponseStatus(HttpStatus.CREATED)
    public IDResponse addReply(
            @PathVariable("commentId") Long commentId,
            Authentication authentication,
            @Valid @RequestBody NewCommentDto newCommentDto
    ) {
        return commentService.addReply(commentId, (Long) authentication.getPrincipal(), newCommentDto);
    }

    @GetMapping("/comments/{commentId}/replies")
    public Page<CommentDto> getRepliesOfComment(
            @PathVariable("commentId") Long commentId,
            Pageable page
    ) {
        return commentService.getRepliesOfComment(commentId, page);
    }

    @PostMapping("/comments/{commentId}/reports")
    @ResponseStatus(HttpStatus.CREATED)
    public void reportComment(
            @PathVariable("commentId") Long commentId,
            Authentication authentication,
            @Valid @RequestBody NewCommentReportDto newCommentReportDto
    ) {
        commentService.reportComment(commentId, (Long) authentication.getPrincipal(), newCommentReportDto);
    }

    @GetMapping("/comments/reports")
    public CommentWithReportsDto getReportsOnComment(Pageable page) {
        return commentService.getReportsOnComment(page);
    }

    @GetMapping("/comments/{commentId}/reports")
    public Slice<CommentReportDto> getReportsByComment(
            @PathVariable("commentId") Long commentId,
            Pageable page
    ) {
        return commentService.getReportsByComment(commentId, page);
    }
}
