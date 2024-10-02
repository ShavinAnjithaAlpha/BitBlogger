package org.bitmonsters.tagservice.controller;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.tagservice.service.TagBatchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/tags/batch")
@RequiredArgsConstructor
public class TagBatchController {

    private final TagBatchService service;

    @PostMapping
    public List<?> getTagsAsBatch(
            @RequestBody List<Integer> tagIds,
            @RequestParam(value = "verbose", defaultValue = "0") Boolean verbose
    ) {
        return service.getTagsAsBatch(tagIds, verbose);
    }
}
