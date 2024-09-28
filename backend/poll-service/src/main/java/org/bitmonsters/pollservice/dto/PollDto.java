package org.bitmonsters.pollservice.dto;

import lombok.Builder;
import lombok.Data;
import org.bitmonsters.pollservice.model.PollType;

import java.util.List;

@Builder
public record PollDto(
        Long id,
        String question,
        String photo,
        PollType type,
        Boolean expired,
        DurationDto remaining,
        Boolean optionalAnswerAllowed,
        Boolean hasAnswer,
        List<Tag> tags,
        List<PollAnswer> answers
) {

    @Data
    @Builder
    static public class PollAnswer {
        private Integer answerId;
        private String answer;
    }
}
