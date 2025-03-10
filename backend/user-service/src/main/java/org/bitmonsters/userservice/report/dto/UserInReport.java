package org.bitmonsters.userservice.report.dto;

import lombok.Builder;

@Builder
public record UserInReport(
        Long id,
        String name,
        String username,
        String email,
        String profileImage
) {
}
