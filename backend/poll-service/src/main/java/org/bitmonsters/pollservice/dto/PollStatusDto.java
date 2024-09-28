package org.bitmonsters.pollservice.dto;

import lombok.Builder;
import org.bitmonsters.pollservice.model.AnswerStatus;

import java.util.List;

@Builder
public record PollStatusDto(
        List<PollAnswerStatusRecord> answerStatuses
) {

    @Builder
    public record PollAnswerStatusRecord(
            Integer answerId,
            AnswerStatus status
    ) {}
}
