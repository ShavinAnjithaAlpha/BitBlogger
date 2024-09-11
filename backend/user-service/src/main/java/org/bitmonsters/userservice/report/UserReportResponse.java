package org.bitmonsters.userservice.report;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserReportResponse(
        Long id,
        UserInReport user,
        UserInReport reporter,
        String note,
        LocalDateTime createdAt
) {
}
