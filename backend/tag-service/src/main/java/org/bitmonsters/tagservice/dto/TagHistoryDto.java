package org.bitmonsters.tagservice.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record TagHistoryDto(
        TagDto tag,
        List<TagHistoryRecord> history
) {
}
