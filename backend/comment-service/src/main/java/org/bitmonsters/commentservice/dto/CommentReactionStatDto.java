package org.bitmonsters.commentservice.dto;

import lombok.Builder;
import org.bitmonsters.commentservice.model.LikeStatus;

import java.util.Map;

@Builder
public record CommentReactionStatDto(
        Map<LikeStatus, Long> count
) {
}
