package org.bitmonsters.pollservice.client.feign;

import org.bitmonsters.pollservice.client.config.FeignTagClientConfig;
import org.bitmonsters.pollservice.client.fallback.TagClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "TAG-SERVICE", fallback = TagClientFallback.class, configuration = FeignTagClientConfig.class)
public interface TagClient {

    @GetMapping("/api/v1/tag/{tagId}")
    TagResponse getTag(@PathVariable("tagId") Integer tagId);

}
