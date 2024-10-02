package org.bitmonsters.tagservice.controller;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.tagservice.dto.*;
import org.bitmonsters.tagservice.service.TagService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            @RequestHeader("userId") Long userId,
            @Validated @RequestBody NewTagDto newTagDto
    ) {
        return service.createTag(newTagDto, userId);
    }

    @PutMapping("/{tagId}")
    public ResponseEntity<?> updateTag(
            @PathVariable Integer tagId,
            @RequestHeader("userID") Long userId,
            @Validated @RequestBody NewTagDto newTagDto
    ) {
        service.updateTag(tagId, newTagDto, userId);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{tagId}")
    public ResponseEntity<?> deleteTag(
            @PathVariable("tagId") Integer tagId,
            @RequestHeader("userId") Long userId
    ) {
        service.deleteTag(tagId, userId);
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
            @PathVariable("postId") Long postId,
            @PathVariable("tagId") Integer tagId,
            @RequestHeader("userId") Long userId
    ) {
        service.addTagToPost(postId, tagId, userId);
    }

    @DeleteMapping("posts/{postId}/{tagId}")
    public void removeTagFromPost(
            @PathVariable("postId") Long postId,
            @PathVariable("tagId") Integer tagId,
            @RequestHeader("userId") Long userId
    ) {
        service.removeTagFromPost(postId, tagId, userId);
    }

    @GetMapping("posts/{postId}")
    public List<TagDto> getTagOfPost(
            @PathVariable("postId") Long postId
    ) {
        return service.getTagsOfPost(postId);
    }

    @PostMapping("posts/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTagsToPost(
            @PathVariable("postId") Long postId,
            @Validated @RequestBody TagList tagList,
            @RequestHeader("userId") Long userId
    ) {
        service.addTagsToPost(postId, userId, tagList);
    }

    @GetMapping("/{tagId}/posts")
    public Slice<Long> getPostOfTag(
            @PathVariable("tagId") Integer tagId
    ) {
        return service.getPostsOfTag(tagId);
    }

    @GetMapping("/{tagId}/history")
    public TagHistoryDto getTagHistory(
            @PathVariable("tagId") Integer tagId,
            @RequestHeader("userId") Long userId
    ) {
        return service.getTagHistory(tagId, userId);
    }
}
