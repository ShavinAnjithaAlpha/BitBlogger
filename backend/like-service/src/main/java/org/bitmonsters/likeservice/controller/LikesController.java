package org.bitmonsters.likeservice.controller;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.likeservice.dto.*;
import org.bitmonsters.likeservice.model.LikeStatus;
import org.bitmonsters.likeservice.service.LikeService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LikesController {

    private final LikeService service;

    @PostMapping("/posts/{postId}/likes")
    public ResponseEntity<?> likePost(
            @PathVariable("postId") Long postId,
            Authentication authentication,
            @RequestBody LikeStatus likeStatus
    ) {
        service.likePost(postId, (Long) authentication.getPrincipal(), likeStatus);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @DeleteMapping("/posts/{postId}/likes")
    public ResponseEntity<?> removeLike(
            @PathVariable("postId") Long postId,
            Authentication authentication
    ) {
        service.removeLike(postId, (Long) authentication.getPrincipal());
        return ResponseEntity.ok(null);
    }

    @GetMapping("/posts/{postId}/likes")
    public CassandraPage<PostLikeWithUserDto> getLikeOfPost(
            @PathVariable("postId") Long postId,
            @Nullable @RequestParam("status") LikeStatus likeType,
            Pageable page
            ) {
        return service.getPostLikes(postId, page, likeType);
    }

    @GetMapping("/likes/me")
    public CassandraPage<UserLikeDto> getLikesOFAuthenticatedUser(
            Authentication authentication,
            @Nullable @RequestParam("status") LikeStatus likeType,
            Pageable page
    ) {
        return service.likesOfUser((Long) authentication.getPrincipal(), likeType, page);
    }

    @GetMapping("/posts/{postId}/likes/me")
    public ResponseEntity<?> checkLikeOfUserOnPost(
            @PathVariable("postId") Long postId,
            Authentication authentication
    ) {
        var like = service.findLikeByPostAndUser(postId, (Long) authentication.getPrincipal());
        if (like.isPresent()) {
            return ResponseEntity.ok(like.get().getLikeStatus());
        } else {
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/likes/me/count")
    public LikeCountDto getLikeCountOfAuthenticatedUser(
            Authentication authentication
    ) {
        return service.findLikeCountOfUser((Long) authentication.getPrincipal());
    }

    @GetMapping("/posts/{postId}/likes/count")
    public LikeCountDto getLikeCountOfPost(
            @PathVariable("postId") Long postId
    ) {
        return service.findLikeCountOfPost(postId);
    }

    @GetMapping("/likes/global/count")
    public LikeCountDto countAll() {
        return service.countAll();
    }

}
