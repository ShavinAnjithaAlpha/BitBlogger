package org.bitmonsters.commentservice.dto;

import org.bitmonsters.commentservice.model.LikeStatus;

import java.time.LocalDateTime;

public record CommentReactDto(
        Long user,
        LikeStatus status,
        LocalDateTime likedAt
) {
}
