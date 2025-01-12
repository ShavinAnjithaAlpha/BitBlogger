package org.bitmonsters.contentservice.client.fallback;

import org.bitmonsters.contentservice.client.feign.TopicClient;
import org.bitmonsters.contentservice.client.feign.TopicDto;
import org.springframework.stereotype.Component;

@Component
public class TopicClientFallback implements TopicClient {

    @Override
    public TopicDto getTopic(Integer topicId) {
        return TopicDto.builder().build();
    }
}
