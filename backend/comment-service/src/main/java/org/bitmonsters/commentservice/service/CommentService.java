package org.bitmonsters.commentservice.service;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.commentservice.dto.*;
import org.bitmonsters.commentservice.exception.CommentException;
import org.bitmonsters.commentservice.exception.CommentNotFoundException;
import org.bitmonsters.commentservice.exception.PostNotFoundException;
import org.bitmonsters.commentservice.model.Comment;
import org.bitmonsters.commentservice.repository.CommentReportRepository;
import org.bitmonsters.commentservice.repository.CommentRepository;
import org.bitmonsters.commentservice.util.SanitizationService;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentReportRepository commentReportRepository;
    private final CommentMapper mapper;
    private final SanitizationService sanitizationService;

    public IDResponse addComment(String postId, Long userId, NewCommentDto newCommentDto) {
        // sanitize the input using jsoup library
        String sanitizedContent = sanitizationService.sanitizeInput(newCommentDto.content());

        // check whether if the post exists in the post service
        if (!isPostExists(postId)) {
            throw new PostNotFoundException(String.format("post with id %s is not found", postId));
        }

        var comment = commentRepository.save(mapper.toComment(postId, userId, sanitizedContent));
        // return the id od the comment
        return new IDResponse(comment.getId());
    }

    private boolean isPostExists(String postId) {
        return true;
    }

    public Page<CommentDto> getCommentOfPost(String postId, Pageable page) {
        return commentRepository.findAllByPostId(postId, page)
                .map(mapper::toCommentDto);
    }

    public Long getCommentCountOfPost(String postId) {
        return commentRepository.countAllByPostId(postId);
    }


    public CommentDto getCommentById(Long commentId) {
        var comment = findCommentById(commentId);
        return mapper.toCommentDto(comment);
    }


    public void updateComment(Long commentId, Long userId, NewCommentDto newCommentDto) {
        var comment = findCommentById(commentId);
        // check whether comment author is really the one who update it
        if (!userId.equals(comment.getUserId())) {
            throw new CommentException(String.format("comment with id %d does not belong to user with %d", commentId, userId), HttpStatus.FORBIDDEN);
        }

        // sanitize the input content of the comment update request
        String sanitizedContent = Jsoup.clean(newCommentDto.content(), Safelist.basicWithImages());
        // update the relevant field using
        comment.setContent(sanitizedContent);
        comment.setModifiedAt(LocalDateTime.now());

        commentRepository.save(comment);
    }

    public void deleteComment(Long commentId, Long userId) {
        commentRepository.deleteById(commentId);
    }

    public IDResponse addReply(Long commentId, Long userId, NewCommentDto newCommentDto) {
        var comment = findCommentById(commentId);
        // check whether if the given comment has a parent and if had, change the parent comment to it
        var parentComment = comment.getParentComment() != null ? comment.getParentComment() : comment;
        // sanitize the comment content
        String sanitizedContent = sanitizationService.sanitizeInput(newCommentDto.content());
        var reply = commentRepository.save(mapper.toReply(parentComment, userId, sanitizedContent));
        // return the ID of the reply
        return new IDResponse(reply.getId());
    }

    public Page<CommentDto> getRepliesOfComment(Long commentId, Pageable page) {
        var comment = findCommentById(commentId);
        return commentRepository.findAllByParentCommentId(commentId, page)
                .map(mapper::toCommentDto);

    }

    public void reportComment(Long commentId, Long userId, NewCommentReportDto newCommentReportDto) {
        var comment = findCommentById(commentId);
        // save the report in the database
        commentReportRepository.save(mapper.toCommentReport(comment, userId, newCommentReportDto));
    }

    public CommentWithReportsDto getReportsOnComment(Pageable page) {
        // get reports grouped by comment and page these results
        var reportedComments = commentReportRepository.getDistinctReportsByComments(page);
        // generate a list of comments with their reports count
        List<CommentWithReportsDto.CommentWithReportsRecord> commentWithReportsRecords =
                reportedComments.getContent().stream()
                        .map(mapper::toCommentWithReportsRecord)
                        .toList();

        return CommentWithReportsDto.builder()
                .reports(commentWithReportsRecords)
                .count(reportedComments.getNumberOfElements())
                .hasNext(reportedComments.hasNext())
                .hasPrevious(reportedComments.hasPrevious())
                .isFirst(reportedComments.isFirst())
                .isLast(reportedComments.isLast())
                .build();
    }

    public Slice<CommentReportDto> getReportsByComment(Long commentId, Pageable page) {
        // get reports on specified comment
        return commentReportRepository.findAllByCommentId(commentId, page)
                .map(mapper::toCommentReportDto);
    }

    private Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(
                        () -> new CommentNotFoundException(String.format("comment with id %d id not found", commentId))
                );
    }
}
