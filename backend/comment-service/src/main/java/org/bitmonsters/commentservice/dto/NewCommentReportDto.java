package org.bitmonsters.commentservice.dto;

import org.bitmonsters.commentservice.model.CommentReportStatus;

public record NewCommentReportDto(
        CommentReportStatus reason,
        String otherInfo
) {
}
