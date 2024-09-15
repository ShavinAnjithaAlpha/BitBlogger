package org.bitmonsters.userservice.user.dto;

import lombok.Builder;

@Builder
public record UserLinkResponse(
        String platform,
        String url,
        Boolean custom
) {
}
