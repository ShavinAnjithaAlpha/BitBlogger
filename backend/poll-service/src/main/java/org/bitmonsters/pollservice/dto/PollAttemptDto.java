package org.bitmonsters.pollservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.bitmonsters.pollservice.model.AnswerStatus;

public record PollAttemptDto(
        @NotNull(message = "answer id is required")
        Integer answerId,
        @NotNull(message = "answer is required")
        @Pattern(regexp = "^[a-zA-Z0-9.,!?()\\-\\s]+$", message = "answer contains invalid characters")
        String answer,
        AnswerStatus status
) {
}
