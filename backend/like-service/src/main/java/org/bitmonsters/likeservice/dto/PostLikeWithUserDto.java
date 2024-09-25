package org.bitmonsters.likeservice.dto;

import lombok.Builder;
import org.bitmonsters.likeservice.client.feign.UserResponse;
import org.bitmonsters.likeservice.model.LikeStatus;

import java.time.Instant;

@Builder
public record PostLikeWithUserDto(
        UserResponse user,
        LikeStatus likeStatus,
        Instant likedAt
) {
}
