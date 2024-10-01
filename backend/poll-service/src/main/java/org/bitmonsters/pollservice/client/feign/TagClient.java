package org.bitmonsters.pollservice.client.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.bitmonsters.pollservice.client.config.FeignTagClientConfig;
import org.bitmonsters.pollservice.client.fallback.TagClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "TAG-SERVICE", fallback = TagClientFallback.class, configuration = FeignTagClientConfig.class)
public interface TagClient {

    @CircuitBreaker(name = "TAG-SERVICE", fallbackMethod = "fallbackGetTag")
    @GetMapping("/api/v1/tags/{tagId}")
    TagResponse getTag(@PathVariable("tagId") Integer tagId);

    default TagResponse fallbackGetTag(Throwable throwable) {
        return TagResponse.builder().build();
    }

}
