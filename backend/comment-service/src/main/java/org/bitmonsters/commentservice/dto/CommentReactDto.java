package org.bitmonsters.commentservice.dto;

import lombok.Builder;
import org.bitmonsters.commentservice.model.LikeStatus;

import java.time.LocalDateTime;

@Builder
public record CommentReactDto(
        Long user,
        LikeStatus status,
        LocalDateTime likedAt
) {
}
