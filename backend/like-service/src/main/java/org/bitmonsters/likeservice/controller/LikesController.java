package org.bitmonsters.likeservice.controller;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.likeservice.dto.CassandraPage;
import org.bitmonsters.likeservice.dto.PostLikeDto;
import org.bitmonsters.likeservice.dto.UserLikeDto;
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
    public CassandraPage<PostLikeDto> getLikeOfPost(
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





}
