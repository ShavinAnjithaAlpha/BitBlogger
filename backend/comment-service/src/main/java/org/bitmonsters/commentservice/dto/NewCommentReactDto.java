package org.bitmonsters.commentservice.dto;

import org.bitmonsters.commentservice.model.LikeStatus;

public record NewCommentReactDto(
        LikeStatus likeStatus
) {
}
