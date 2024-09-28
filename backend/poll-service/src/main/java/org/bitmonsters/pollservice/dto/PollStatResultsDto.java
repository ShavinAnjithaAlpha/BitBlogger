package org.bitmonsters.pollservice.dto;

import lombok.Builder;
import org.bitmonsters.pollservice.model.AnswerStatus;

@Builder
public record PollStatResultsDto(
        AnswerStatus status
) {
}
