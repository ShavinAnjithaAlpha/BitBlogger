package org.bitmonsters.userservice.user;

import lombok.Builder;

@Builder
public record UserLinkResponse(
        String platform,
        String url,
        Boolean custom
) {
}
