package org.bitmonsters.pollservice.client.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.bitmonsters.pollservice.client.config.FeignTagClientConfig;
import org.bitmonsters.pollservice.client.fallback.TagClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "TAG-SERVICE", fallback = TagClientFallback.class, configuration = FeignTagClientConfig.class)
public interface TagClient {

    @CircuitBreaker(name = "TAG-SERVICE", fallbackMethod = "fallbackGetTag")
    @GetMapping("/api/v1/tags/{tagId}")
    TagResponse getTag(@PathVariable("tagId") Integer tagId);

    @CircuitBreaker(name = "TAG-SERVICE", fallbackMethod = "fallbackGetTagAsBatch")
    @PostMapping("/api/v1/tags/batch")
    List<String> getTagsAsBatch(@RequestBody List<Integer> tagIds, @RequestParam("verbose") Boolean verbose);

    default TagResponse fallbackGetTag(Throwable throwable) {
        return TagResponse.builder()
                        .name("TAG")
                        .build();
    }

    default List<String> fallbackGetTagAsBatch(List<Integer> tagIds) {
        return new ArrayList<>(0);
    }

}
