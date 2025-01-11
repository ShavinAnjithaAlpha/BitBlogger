package org.bitmonsters.contentservice.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "TAG-SERVICE")
public interface TagClient {

    @PostMapping("/api/v1/tags/batch")
    List<TagDto> getTagsAsBatch(
            @RequestBody List<Integer> tagIds,
            @RequestParam(value = "verbose", defaultValue = "0") Boolean verbose
    );

}
