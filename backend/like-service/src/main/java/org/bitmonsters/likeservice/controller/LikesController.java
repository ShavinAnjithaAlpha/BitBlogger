package org.bitmonsters.likeservice.controller;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.likeservice.dto.*;
import org.bitmonsters.likeservice.model.LikeStatus;
import org.bitmonsters.likeservice.service.LikeService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor
public class LikesController {

    private final LikeService service;

    @PostMapping("/{postId}")
    public ResponseEntity<?> likePost(
            @PathVariable("postId") Long postId,
            @RequestHeader("userId") Long userId,
            @RequestBody LikeStatus likeStatus
    ) {
        service.likePost(postId, userId, likeStatus);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> removeLike(
            @PathVariable("postId") Long postId,
            @RequestHeader("userId") Long userId
    ) {
        service.removeLike(postId, userId);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{postId}")
    public CassandraPage<PostLikeWithUserDto> getLikeOfPost(
            @PathVariable("postId") Long postId,
            @Nullable @RequestParam("status") LikeStatus likeType,
            Pageable page
            ) {
        return service.getPostLikes(postId, page, likeType);
    }

    @GetMapping("/me")
    public CassandraPage<UserLikeDto> getLikesOFAuthenticatedUser(
            @RequestHeader("userId") Long userId,
            @Nullable @RequestParam("status") LikeStatus likeType,
            Pageable page
    ) {
        return service.likesOfUser(userId, likeType, page);
    }

    @GetMapping("/me/{postId}")
    public ResponseEntity<?> checkLikeOfUserOnPost(
            @PathVariable("postId") Long postId,
            @RequestHeader("userId") Long userId
    ) {
        var like = service.findLikeByPostAndUser(postId, userId);
        if (like.isPresent()) {
            return ResponseEntity.ok(like.get().getLikeStatus());
        } else {
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/me/count")
    public LikeCountDto getLikeCountOfAuthenticatedUser(
            @RequestHeader("userId") Long userId
    ) {
        return service.findLikeCountOfUser(userId);
    }

    @GetMapping("/count/{postId}")
    public LikeCountDto getLikeCountOfPost(
            @PathVariable("postId") Long postId
    ) {
        return service.findLikeCountOfPost(postId);
    }

    @GetMapping("/global/count")
    public LikeCountDto countAll() {
        return service.countAll();
    }

}
