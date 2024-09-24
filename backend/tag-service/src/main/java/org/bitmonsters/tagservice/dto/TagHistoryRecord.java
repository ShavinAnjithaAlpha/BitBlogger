package org.bitmonsters.tagservice.dto;

import lombok.Builder;
import org.bitmonsters.tagservice.model.TagAction;

import java.time.LocalDateTime;

@Builder
public record TagHistoryRecord(
        Integer id,
        String name,
        String description,
        String icon,
        Long changedBy,
        LocalDateTime changedAt,
        TagAction action
) {
}
