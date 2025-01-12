package org.bitmonsters.contentservice.controller;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.contentservice.dto.*;
import org.bitmonsters.contentservice.service.ContentService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IDResponse createPost(@Validated @RequestBody NewPostDto newPostDto, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return contentService.createNewPost(newPostDto, userId);
    }

    @PostMapping("/{draftId}")
    @ResponseStatus(HttpStatus.CREATED)
    public IDResponse createPostFromDraft(@PathVariable("draftId") String draftId,
                                          @RequestBody PostFromDraftDto postFromDraftDto,
                                          Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return contentService.createPostFromDraft(draftId, postFromDraftDto, userId);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable("postId") String postId, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        contentService.deletePost(postId, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping("/drafts")
    @ResponseStatus(HttpStatus.CREATED)
    public IDResponse createDraft(@Validated @RequestBody PostDraftDto postDraftDto, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return contentService.createDraftPost(postDraftDto, userId);
    }

    @PutMapping("/drafts/{draftId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateDraft(@PathVariable("draftId") String draftId,
                            @Validated @RequestBody PostDraftDto postDraftDto,
                            Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        contentService.updateDraftPost(draftId, postDraftDto, userId);
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
    public List<PostDto> getPostOfAuthenticatedUser(Pageable page, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return contentService.getPostsByUserId(userId, page);
    }

    @GetMapping("/topics/{topicId}")
    public List<PostDto> getPostsByTopicId(@PathVariable("topicId") Integer topicId, Pageable page) {
        return contentService.getPostsByTopicId(topicId, page);
    }

}
