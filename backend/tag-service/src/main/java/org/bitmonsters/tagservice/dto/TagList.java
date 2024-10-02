package org.bitmonsters.tagservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record TagList(
        @NotNull(message = "tags is required")
        @NotEmpty(message = "tags cannot be empty")
        List<Integer> tags
) {
}
