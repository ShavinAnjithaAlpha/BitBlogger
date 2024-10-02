package org.bitmonsters.pollservice.dto;

import lombok.Builder;
import org.bitmonsters.pollservice.model.AnswerStatus;

import java.util.List;
import java.util.Map;

@Builder
public record PollStatResultsDto(
        List<PollAnswer> answerCounts,
        Long totalCount,
        Long totalRightAnswers,
        Long totalWrongAnswers,
        Long optionalAnswerCount

) {

    @Builder
    public record PollAnswer(
            Integer answerId,
            String answer,
            AnswerStatus status,
            Long count
    ) {}
}
