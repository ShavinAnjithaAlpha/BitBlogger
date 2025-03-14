package org.bitmonsters.tagservice.dto;

import lombok.Builder;
import org.bitmonsters.tagservice.client.feign.UserResponse;
import org.bitmonsters.tagservice.model.TagAction;

import java.time.LocalDateTime;

@Builder
public record TagHistoryRecord(
        Integer id,
        String name,
        String description,
        String icon,
        UserResponse changedBy,
        LocalDateTime changedAt,
        TagAction action
) {
}
