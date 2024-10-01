package org.bitmonsters.pollservice.dto;

import org.bitmonsters.pollservice.dto.constraint.OneFieldRequired;

import java.util.List;

@OneFieldRequired(message = "only answer or optional answer is accepted: not both")
public record NewPollAnswerDto(
        List<Integer> answers,
        String optionalAnswer,
        Boolean isPublic
) {

}
