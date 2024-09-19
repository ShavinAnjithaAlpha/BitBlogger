package org.bitmonsters.userservice.me.dto;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.bitmonsters.userservice.report.UserReportReason;

public record UserReportDto(
        @NotNull
        Long userId,
        @NotNull
        @Enumerated
        UserReportReason reason,
        @Size(max = 512, message = "note should be maximum 512 characters")
        String note
) {
}
