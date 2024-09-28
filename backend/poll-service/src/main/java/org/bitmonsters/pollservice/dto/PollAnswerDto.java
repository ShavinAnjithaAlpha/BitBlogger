package org.bitmonsters.pollservice.dto;

import org.bitmonsters.pollservice.model.AnswerStatus;

public record PollAnswerDto(
        String answer,
        AnswerStatus status
) {
}
