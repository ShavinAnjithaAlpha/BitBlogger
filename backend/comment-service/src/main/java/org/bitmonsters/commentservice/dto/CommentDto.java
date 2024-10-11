package org.bitmonsters.commentservice.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommentDto(
        String content,
        Long user,
        LocalDateTime commentedAt,
        LocalDateTime modifiedAt,
        Integer replyCount,
        Integer reactionCount
) {
}
