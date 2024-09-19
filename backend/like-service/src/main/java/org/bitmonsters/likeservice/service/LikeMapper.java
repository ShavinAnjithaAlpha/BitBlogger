package org.bitmonsters.likeservice.service;

import org.bitmonsters.likeservice.dto.PostLikeDto;
import org.bitmonsters.likeservice.dto.UserLikeDto;
import org.bitmonsters.likeservice.model.LikeByPostAndStatus;
import org.bitmonsters.likeservice.model.LikeByPostAndUser;
import org.bitmonsters.likeservice.model.LikeByUserAndStatus;
import org.bitmonsters.likeservice.model.LikeStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class LikeMapper {

    public LikeByPostAndUser toLikeByPostAndUser(Long postId, Long userId, LikeStatus likeStatus, Instant likedAt) {
        return LikeByPostAndUser.builder()
                .key(LikeByPostAndUser.LikeKey.builder().postId(postId).userId(userId).build())
                .likeStatus(likeStatus.getValue())
                .likedAt(likedAt)
                .build();
    }

    public LikeByPostAndStatus toLikeByPostAndStatus(Long postId, Long userId, LikeStatus likeStatus, Instant likedAt) {
        return LikeByPostAndStatus.builder()
                .key(LikeByPostAndStatus.LikeKey
                        .builder()
                        .postId(postId)
                        .likeStatus(likeStatus.getValue())
                        .userId(userId)
                        .build())
                .likedAt(likedAt)
                .build();
    }

    public LikeByUserAndStatus toLikeByUserAndStatus(Long postId, Long userId, LikeStatus likeStatus, Instant likedAt) {
        return LikeByUserAndStatus.builder()
                .key(LikeByUserAndStatus.LikeKey.builder()
                        .userId(userId)
                        .likeStatus(likeStatus.getValue())
                        .postId(postId)
                        .build())
                .likedAt(likedAt)
                .build();
    }

    public PostLikeDto toPostLikeDto(LikeByPostAndStatus likeByPostAndStatus) {
        return PostLikeDto.builder()
                .userId(likeByPostAndStatus.getKey().getUserId())
                .likeStatus(likeByPostAndStatus.getKey().getLikeStatus())
                .likedAt(likeByPostAndStatus.getLikedAt())
                .build();
    }

    public PostLikeDto toPostLikeDto(LikeByPostAndUser likeByPostAndUser) {
        return PostLikeDto.builder()
                .userId(likeByPostAndUser.getKey().getUserId())
                .likeStatus(likeByPostAndUser.getLikeStatus())
                .likedAt(likeByPostAndUser.getLikedAt())
                .build();
    }

    public UserLikeDto toUserLikeDto(LikeByUserAndStatus likeByUserAndStatus) {
        return UserLikeDto.builder()
                .postId(likeByUserAndStatus.getKey().getPostId())
                .likeStatus(likeByUserAndStatus.getKey().getLikeStatus())
                .likedAt(likeByUserAndStatus.getLikedAt())
                .build();
    }
}
