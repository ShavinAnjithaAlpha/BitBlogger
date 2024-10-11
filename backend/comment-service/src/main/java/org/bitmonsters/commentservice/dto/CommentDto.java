package org.bitmonsters.commentservice.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommentDto(
        Long id,
        String content,
        Long user,
        LocalDateTime commentedAt,
        LocalDateTime modifiedAt,
        Integer replyCount,
        Integer reactionCount
) {
}
