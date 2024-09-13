package org.bitmonsters.userservice.link;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record UserLinkDto(
        Integer platformId,
        @Pattern(regexp = "^[A-Za-z0-9+_.-:/]+.[A-Za-z]+$", message = "Invalid URL format")
        String url,
        Boolean custom
) {
}
