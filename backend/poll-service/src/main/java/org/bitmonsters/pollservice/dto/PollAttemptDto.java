package org.bitmonsters.pollservice.dto;


import lombok.Builder;
import org.bitmonsters.pollservice.client.feign.UserResponse;

import java.time.LocalDateTime;

@Builder
public record PollAttemptDto(
        UserResponse user,
        Integer answerId,
        LocalDateTime answeredAt,
        String optionalAnswer
) {
}
