package org.bitmonsters.commentservice.dto;

import lombok.Builder;
import org.bitmonsters.commentservice.model.CommentReportStatus;

import java.time.LocalDateTime;

@Builder
public record CommentReportDto(
        Long id,
        Long user,
        CommentReportStatus reason,
        String otherInfo,
        LocalDateTime reportedAt
) {
}
