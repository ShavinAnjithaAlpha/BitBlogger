package org.bitmonsters.likeservice.dto;

import lombok.Builder;
import org.bitmonsters.likeservice.model.LikeStatus;

import java.time.Instant;

@Builder
public record UserLikeDto(
        Long postId,
        LikeStatus likeStatus,
        Instant likedAt
) {
}
