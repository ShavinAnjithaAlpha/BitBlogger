package org.bitmonsters.contentservice.client.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.bitmonsters.contentservice.client.config.FeignTopicClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "TOPIC-SERVICE", configuration = FeignTopicClientConfiguration.class)
public interface TopicClient {

    @CircuitBreaker(name = "TOPIC-SERVICE", fallbackMethod = "fallbackGetTopic")
    @GetMapping("api/v1/topics/{topicId}")
    TopicDto getTopic(@PathVariable("topicId") Integer topicId);

    default TopicDto fallbackGetTopic(@PathVariable("topicId") Integer topicId) {
        return TopicDto.builder().build();
    }

}
