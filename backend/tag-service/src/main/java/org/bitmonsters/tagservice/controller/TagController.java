package org.bitmonsters.tagservice.controller;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.tagservice.dto.*;
import org.bitmonsters.tagservice.service.TagService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IdResponse createTag(
            Authentication authentication,
            @Validated @RequestBody NewTagDto newTagDto
    ) {
        return service.createTag(newTagDto, (Long) authentication.getPrincipal());
    }

    @PutMapping("/{tagId}")
    public ResponseEntity<?> updateTag(
            @PathVariable Integer tagId,
            Authentication authentication,
            @Validated @RequestBody NewTagDto newTagDto
    ) {
        service.updateTag(tagId, newTagDto, (Long) authentication.getPrincipal());
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{tagId}")
    public ResponseEntity<?> deleteTag(
            @PathVariable("tagId") Integer tagId,
            Authentication authentication
    ) {
        service.deleteTag(tagId, (Long) authentication.getPrincipal());
        return ResponseEntity.ok(null);
    }

    @GetMapping
    public Slice<TagDto> getTags(
            Pageable page
    ) {
        return service.getTags(page);
    }

    @GetMapping("/{tagId}")
    public TagDto getTag(
            @PathVariable("tagId") Integer tagId
    ) {
        return service.getTag(tagId);
    }

    @PostMapping("posts/{postId}/{tagId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTagToPost(
            @PathVariable("postId") String postId,
            @PathVariable("tagId") Integer tagId,
            Authentication authentication
    ) {
        service.addTagToPost(postId, tagId, (Long) authentication.getPrincipal());
    }

    @DeleteMapping("posts/{postId}/{tagId}")
    public void removeTagFromPost(
            @PathVariable("postId") String postId,
            @PathVariable("tagId") Integer tagId,
            Authentication authentication
    ) {
        service.removeTagFromPost(postId, tagId, (Long) authentication.getPrincipal());
    }

    @GetMapping("posts/{postId}")
    public List<TagDto> getTagOfPost(
            @PathVariable("postId") String postId
    ) {
        return service.getTagsOfPost(postId);
    }

    @PostMapping("posts/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTagsToPost(
            @PathVariable("postId") String postId,
            @Validated @RequestBody TagList tagList,
            Authentication authentication
    ) {
        service.addTagsToPost(postId, (Long) authentication.getPrincipal(), tagList);
    }

    @GetMapping("/{tagId}/posts")
    public Slice<String> getPostOfTag(
            @PathVariable("tagId") Integer tagId
    ) {
        return service.getPostsOfTag(tagId);
    }

    @GetMapping("/{tagId}/history")
    public TagHistoryDto getTagHistory(
            @PathVariable("tagId") Integer tagId,
            Authentication authentication
    ) {
        return service.getTagHistory(tagId, (Long) authentication.getPrincipal());
    }
}
