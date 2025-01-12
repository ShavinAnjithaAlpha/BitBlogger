package org.bitmonsters.contentservice.client.feign;

import lombok.Builder;

import java.util.List;

@Builder
public record TagList(
        List<Integer> tags
) {
}
