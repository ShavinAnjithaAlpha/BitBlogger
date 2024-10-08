package org.bitmonsters.userservice.user.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record UserLinkResponse(
        String platform,
        String url,
        Boolean custom
) implements Serializable {
}
