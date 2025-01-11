package org.bitmonsters.contentservice.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "TOPIC-SERVICE")
public interface TopicClient {

    @GetMapping("api/v1/topics/{topicId}")
    public TopicDto getTopic(@PathVariable("topicId") Integer topicId);

}
