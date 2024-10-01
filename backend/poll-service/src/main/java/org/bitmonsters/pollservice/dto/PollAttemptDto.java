package org.bitmonsters.pollservice.dto;


import lombok.Builder;
import org.bitmonsters.pollservice.client.feign.UserResponse;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record PollAttemptDto(
        UserResponse user,
        List<Integer> answerIds,
        LocalDateTime answeredAt,
        String optionalAnswer
) {
}
