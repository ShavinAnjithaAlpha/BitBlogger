package org.bitmonsters.commentservice.service;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.commentservice.dto.CommentReactDto;
import org.bitmonsters.commentservice.dto.CommentReactionStatDto;
import org.bitmonsters.commentservice.dto.NewCommentReactDto;
import org.bitmonsters.commentservice.exception.CommentLikeException;
import org.bitmonsters.commentservice.exception.CommentNotFoundException;
import org.bitmonsters.commentservice.model.Comment;
import org.bitmonsters.commentservice.model.LikeStatus;
import org.bitmonsters.commentservice.repository.CommentLikeRepository;
import org.bitmonsters.commentservice.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentReactionService {

    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final CommentLikeMapper mapper;

    public Comment getComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
                () -> new CommentNotFoundException(String.format("comment with id %d is not found", commentId))
        );
    }

    public boolean isLikeBefore(Long commentId, Long userId) {
        return commentLikeRepository.findByCommentIdAndUserId(commentId, userId).isPresent();
    }

    public void updateLikeCountInComment(Comment comment, Integer amount) {
        comment.setReplyCount(comment.getReplyCount() + amount);
        commentRepository.save(comment);
    }

    public void reactToComment(Long commentId, Long userId, NewCommentReactDto newCommentReactDto) {
        // find the comment associated with the like
        var comment = getComment(commentId);
        // check whether given user is like before
        if (isLikeBefore(commentId, userId)) {
            throw new CommentLikeException(String.format("user with id %d already react to comment with id %d", userId, commentId));
        }

        // create a like in the like table
        commentLikeRepository.save(mapper.toCommentLike(comment, userId, newCommentReactDto));
        // update the comment reaction count
        updateLikeCountInComment(comment, 1);
    }

    public void removeReact(Long commentId, Long userId) {
        var comment = getComment(commentId);
        // find the reaction associated with the user
        var commentLike = commentLikeRepository.findByCommentIdAndUserId(commentId, userId);
        // remove the like if exists
        if (commentLike.isPresent()) {
            commentLikeRepository.delete(commentLike.get());
            // decrease the reaction count in the comment entity
            updateLikeCountInComment(comment, -1);
        }
    }

    public Page<CommentReactDto> getReactsToComment(Long commentId, Pageable page) {
        return commentLikeRepository.findAllByCommentId(commentId, page)
                .map(mapper::toCommentReactDto);
    }

    public Boolean isReactToComment(Long commentId, Long userId) {
        return isLikeBefore(commentId, userId);
    }

    public void updateCommentReact(Long commentId, Long userId, NewCommentReactDto newCommentReactDto) {
        // get the comment like of the user
        var commentLike = commentLikeRepository.findByCommentIdAndUserId(commentId, userId);
        if (commentLike.isEmpty()) {
            throw new CommentLikeException(String.format("user with id %d didn't react to comment with id %d before", userId, commentId), HttpStatus.NOT_FOUND);
        }

        // otherwise update the comment react
        commentLike.get().setLikeStatus(newCommentReactDto.likeStatus());
        commentLikeRepository.save(commentLike.get());
    }

    public Long getReactionCountOfComment(Long commentId) {
        return commentLikeRepository.countAllByCommentId(commentId);
    }

    public CommentReactionStatDto getCommentReactionStats(Long commentId) {
        // create a map for store the like status and their like count respectively
        Map<LikeStatus, Long> likeCounts = new HashMap<>(LikeStatus.values().length);
        Long totalCount = 0L;

        for (LikeStatus likeStatus: LikeStatus.values()) {
            Long count =  commentLikeRepository.countAllByCommentIdAndLikeStatus(commentId, likeStatus);
            // find the like count of each of the like type
            likeCounts.put(likeStatus, count);
            // increment the total count
            totalCount += count;

        }

        return CommentReactionStatDto.builder()
                .count(likeCounts)
                .totalCount(totalCount)
                .build();

    }
}
