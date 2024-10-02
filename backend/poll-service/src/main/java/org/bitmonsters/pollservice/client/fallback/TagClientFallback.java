package org.bitmonsters.pollservice.client.fallback;

import org.bitmonsters.pollservice.client.feign.TagClient;
import org.bitmonsters.pollservice.client.feign.TagResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagClientFallback implements TagClient {

    @Override
    public TagResponse getTag(Integer tagId) {
        return TagResponse.builder()
                .id(tagId)
                .name("TAG")
                .build();
    }

    @Override
    public List<String> getTagsAsBatch(List<Integer> tagIds, Boolean verbose) {
        return List.of();
    }
}
