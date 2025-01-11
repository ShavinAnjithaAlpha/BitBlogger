package org.bitmonsters.contentservice.controller;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.contentservice.dto.*;
import org.bitmonsters.contentservice.service.ContentService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @PostMapping
    public IDResponse createPost(@Validated @RequestBody NewPostDto newPostDto) {
        Long userId = 2L;
        return contentService.createNewPost(newPostDto, userId);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable("postId") String postId) {
        Long userId = 2L;
        contentService.deletePost(postId, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping("/draft")
    public IDResponse createDraft(@Validated @RequestBody PostDraftDto postDraftDto) {
        Long userId = 2L;
        return contentService.createDraftPost(postDraftDto, userId);
    }

    @GetMapping("/{postId}")
    public FullPostDto getPostById(@PathVariable("postId") String postId) {
        return contentService.getPostById(postId);
    }

    @GetMapping
    public List<PostDto> getLatestPosts(Pageable page) {
        return contentService.getLatestPosts(page);
    }

    @GetMapping("/users/{userId}")
    public List<PostDto> getPostByUSerId(@PathVariable("userId") Long userId, Pageable page) {
        return contentService.getPostsByUserId(userId, page);
    }

    @GetMapping("/me")
    public List<PostDto> getPostOfAuthenticatedUser(Pageable page) {
        Long userId = 2L;
        return contentService.getPostsByUserId(userId, page);
    }

    @GetMapping("/topics/{topicId}")
    public List<PostDto> getPostsByTopicId(@PathVariable("topicId") Integer topicId, Pageable page) {
        return contentService.getPostsByTopicId(topicId, page);
    }

}
