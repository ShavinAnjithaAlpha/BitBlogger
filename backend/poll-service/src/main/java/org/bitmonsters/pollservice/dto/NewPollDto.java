package org.bitmonsters.pollservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.bitmonsters.pollservice.model.PollType;

import java.util.List;

public record NewPollDto(
        @Pattern(regexp = "^[a-zA-Z0-9.,!?()\\-\\s]+$", message = "question content include invalid characters")
        String question,
        @Valid
        DurationDto duration,
        @Pattern(regexp = "^(https?:\\/\\/)?([\\da-z.-]+)\\.([a-z.]{2,6})([\\/\\w .-]*)*\\/?$^(https?:\\/\\/)?([\\da-z.-]+)\\.([a-z.]{2,6})([\\/\\w .-]*)*\\/?$",
                message = "invalid url")
        String photo,
        @NotNull(message = "poll type is required")
        PollType pollType,
        List<Integer> tags,
        Boolean optionalAnswerAllowed,
        Boolean hasAnswer,
        Boolean answerVisibility,
        Boolean voteVisibility,
        @Valid
        @NotEmpty(message = "answers are required")
//        @Size(max = 10, message = "maximum number of answer should be 10")
        List<PollAnswerDto> answers
) {
}
