package org.bitmonsters.contentservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record PostFromDraftDto(
        @Size(max = 100, message = "preview cannot exceeded 100 characters")
        String preview,
        @NotNull(message = "topic id is required")
        Integer topicId,
        List<Integer> tagIds,
        String language,
        Boolean isPinned
) {
}
