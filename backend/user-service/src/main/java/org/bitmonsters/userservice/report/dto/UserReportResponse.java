package org.bitmonsters.userservice.report.dto;

import lombok.Builder;
import org.bitmonsters.userservice.report.UserReportReason;

import java.time.LocalDateTime;

@Builder
public record UserReportResponse(
        Long id,
        UserInReport user,
        UserInReport reporter,
        UserReportReason reason,
        String note,
        LocalDateTime createdAt
) {
}
