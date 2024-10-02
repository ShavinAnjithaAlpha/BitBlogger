package org.bitmonsters.pollservice.dto;

import jakarta.validation.Valid;

public record PollUpdateDto(
        @Valid
        DurationDto timeAdded,
        @Valid
        DurationDto timeRemoved,
        Boolean answerVisibility,
        Boolean voteVisibility
) {
}
