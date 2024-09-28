package org.bitmonsters.pollservice.client.fallback;

import org.bitmonsters.pollservice.client.feign.TagClient;
import org.bitmonsters.pollservice.client.feign.TagResponse;

public class TagClientFallback implements TagClient {

    @Override
    public TagResponse getTag(Integer tagId) {
        return null;
    }
}
