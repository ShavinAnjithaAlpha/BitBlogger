package org.bitmonsters.commentservice.service;

import org.bitmonsters.commentservice.dto.CommentReactDto;
import org.bitmonsters.commentservice.dto.NewCommentReactDto;
import org.bitmonsters.commentservice.model.Comment;
import org.bitmonsters.commentservice.model.CommentLike;
import org.springframework.stereotype.Service;

@Service
public class CommentLikeMapper {
    public CommentLike toCommentLike(Comment comment, Long userId, NewCommentReactDto newCommentReactDto) {
        return CommentLike.builder()
                .comment(comment)
                .userId(userId)
                .likeStatus(newCommentReactDto.likeStatus())
                .build();
    }

    public CommentReactDto toCommentReactDto(CommentLike commentLike) {
        return CommentReactDto.builder()
                .user(commentLike.getUserId())
                .status(commentLike.getLikeStatus())
                .likedAt(commentLike.getLikedAt())
                .build();
    }
}
