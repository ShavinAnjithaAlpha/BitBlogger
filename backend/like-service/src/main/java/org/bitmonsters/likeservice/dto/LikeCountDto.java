package org.bitmonsters.likeservice.dto;

import lombok.Builder;
import org.bitmonsters.likeservice.model.LikeStatus;

import java.util.Map;

@Builder
public record LikeCountDto(
        Map<LikeStatus, Long> count
) {
}
