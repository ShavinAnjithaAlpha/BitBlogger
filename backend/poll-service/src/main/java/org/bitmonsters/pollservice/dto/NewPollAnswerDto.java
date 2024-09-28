package org.bitmonsters.pollservice.dto;

import org.bitmonsters.pollservice.dto.constraint.OneFieldRequired;

import java.util.List;

public record NewPollAnswerDto(
        List<NewPollAnswerRecord> answers,
        Boolean isPublic
) {

    @OneFieldRequired(message = "only answer id or answer is required")
    public record NewPollAnswerRecord(
            Integer answerId,
            String answer
    ) {

    }

}
