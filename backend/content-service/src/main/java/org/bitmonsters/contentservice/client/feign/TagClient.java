package org.bitmonsters.contentservice.client.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.bitmonsters.contentservice.client.config.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "TAG-SERVICE", configuration = FeignClientConfiguration.class)
public interface TagClient {

    @PostMapping("/api/v1/tags/batch")
    List<TagDto> getTagsAsBatch(
            @RequestBody List<Integer> tagIds,
            @RequestParam(value = "verbose", defaultValue = "0") Boolean verbose
    );

    @PostMapping("api/v1/tags/posts/{postId}")
    void addTagsToPost(
            @PathVariable("postId") String postId,
            @Validated @RequestBody TagList tagList
    );

    @CircuitBreaker(name = "TAG-SERVICE", fallbackMethod = "fallbackGetTagOfPost")
    @GetMapping("/api/v1/tags/posts/{postId}")
    List<FullTagDto> getTagOfPost(
            @PathVariable("postId") String postId
    );

    default List<FullTagDto> fallbackGetTagOfPost(@PathVariable("postId") String postId) {
        return List.of();
    }

}
