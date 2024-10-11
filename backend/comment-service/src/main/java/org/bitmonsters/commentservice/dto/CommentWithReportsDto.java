package org.bitmonsters.commentservice.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record CommentWithReportsDto(
        List<CommentWithReportsRecord> reports,
        Integer count,
        Boolean isFirst,
        Boolean isLast,
        Boolean hasNext,
        Boolean hasPrevious
) {

    @Builder
    public record CommentWithReportsRecord(
            CommentDto comment,
            Long numberOfReports
    ) {}
}
