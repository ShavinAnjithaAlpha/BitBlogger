package org.bitmonsters.userservice.interest.dto;

import jakarta.validation.constraints.NotNull;

public record NewInterestDto(
        @NotNull(message = "tag id is required")
        Integer tagId
) {
}
