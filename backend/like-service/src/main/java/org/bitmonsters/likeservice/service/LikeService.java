package org.bitmonsters.likeservice.service;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.likeservice.dto.CassandraPage;
import org.bitmonsters.likeservice.dto.LikeCountDto;
import org.bitmonsters.likeservice.dto.PostLikeDto;
import org.bitmonsters.likeservice.dto.UserLikeDto;
import org.bitmonsters.likeservice.model.GlobalLikeCount;
import org.bitmonsters.likeservice.model.LikeByPostAndUser;
import org.bitmonsters.likeservice.model.LikeStatus;
import org.bitmonsters.likeservice.model.PostLikeCount;
import org.bitmonsters.likeservice.repository.*;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeByPostAndUserRepository likeByPostAndUserRepository;
    private final LikeByPostAndStatusRepository likeByPostAndStatusRepository;
    private final LikeByUserAndStatusRepository likeByUserAndStatusRepository;
    private final PostLikeCountRepository postLikeCountRepository;
    private final GlobalLikeCountRepository globalLikeCountRepository;
    private final LikeMapper mapper;

    public void likePost(Long postId, Long userId, LikeStatus likeStatus) {
        var like = findLikeByPostAndUser(postId, userId);
        if (like.isPresent()) {
            if (like.get().getLikeStatus() == likeStatus) {
                // if the like is same nothing changes
                return;
            } else {
                // remove the previous like on the specified post
                removeLike(postId, userId);
            }
        }

        Instant likedAt = Instant.now();
        likeByPostAndUserRepository.save(mapper.toLikeByPostAndUser(postId, userId, likeStatus, likedAt));
        likeByPostAndStatusRepository.save(mapper.toLikeByPostAndStatus(postId, userId, likeStatus, likedAt));
        likeByUserAndStatusRepository.save(mapper.toLikeByUserAndStatus(postId, userId, likeStatus, likedAt));
        changeLikeCount(postId, likeStatus, 1);
    }

    public void removeLike(Long postId, Long userId) {
        // find the like by searching the likeByPostAndUser repository
        Optional<LikeByPostAndUser> like = likeByPostAndUserRepository.findByKeyPostIdAndKeyUserId(postId, userId);

        if (like.isPresent()) {
            Integer likeStatus = like.get().getLikeStatus().getValue();

            // remove from the three repositories
            likeByPostAndUserRepository.deleteByKeyPostIdAndKeyUserId(postId, userId);
            likeByPostAndStatusRepository.deleteByKeyPostIdAndKeyLikeStatusAndKeyUserId(postId, likeStatus, userId);
            likeByUserAndStatusRepository.deleteByKeyUserIdAndKeyLikeStatusAndKeyPostId(userId, likeStatus, postId);

            changeLikeCount(postId, LikeStatus.fromValue(likeStatus), -1);
        }
    }

    public CassandraPage<PostLikeDto> getPostLikes(Long postId, Pageable page, @Nullable LikeStatus likeType) {
        CassandraPageRequest cassandraPageRequest = CassandraPageRequest.of(page.getPageNumber(), page.getPageSize(), page.getSort());
        if (likeType != null) {
            return new CassandraPage<>(
                    likeByPostAndStatusRepository.findAllByKeyPostIdAndKeyLikeStatus(
                            postId, likeType.getValue(), cassandraPageRequest)
                            .map(mapper::toPostLikeDto));
        } else {
            return new CassandraPage<>(likeByPostAndUserRepository.findAllByKeyPostId(postId, cassandraPageRequest)
                    .map(mapper::toPostLikeDto));
        }

    }

    public CassandraPage<UserLikeDto> likesOfUser(Long userId, @Nullable LikeStatus likeType, Pageable page) {
        CassandraPageRequest cassandraPageRequest = CassandraPageRequest.of(page.getPageNumber(), page.getPageSize(), page.getSort());
        if (likeType != null) {
            return new CassandraPage<>(likeByUserAndStatusRepository.findAllByKeyUserIdAndKeyLikeStatus(userId, likeType.getValue(), cassandraPageRequest)
                    .map(mapper::toUserLikeDto));
        } else {
            return new CassandraPage<>(likeByUserAndStatusRepository.findAllByKeyUserId(userId, cassandraPageRequest)
                    .map(mapper::toUserLikeDto));
        }
    }

    public Boolean isLikeExists(Long postId, Long userId) {
        Optional<LikeByPostAndUser> like = likeByPostAndUserRepository.findByKeyPostIdAndKeyUserId(postId, userId);
        return like.isPresent();
    }

    public Optional<LikeByPostAndUser> findLikeByPostAndUser(Long postId, Long userId) {
        return likeByPostAndUserRepository.findByKeyPostIdAndKeyUserId(postId, userId);
    }

    public LikeCountDto findLikeCountOfUser(Long userId) {
        Map<LikeStatus, Long> count = new HashMap<>();
        for (LikeStatus likeStatus: LikeStatus.values()) {
            count.put(likeStatus, findCountOfUserWithLikeStatus(userId, likeStatus));
        }

        return LikeCountDto.builder()
                .count(count)
                .build();
    }

    public Long findCountOfUserWithLikeStatus(Long userId, LikeStatus likeStatus) {
        return likeByUserAndStatusRepository.countAllByKeyUserIdAndKeyLikeStatus(userId, likeStatus.getValue());
    }

    public LikeCountDto findLikeCountOfPost(Long postId) {
        Map<LikeStatus, Long> count = new HashMap<>();

        for (var likeStatus: LikeStatus.values()) {
            count.put(likeStatus, findCountOfPostWithLikeStatus(postId, likeStatus));
        }

        return LikeCountDto.builder()
                .count(count)
                .build();
    }

    public Long findCountOfPostWithLikeStatus(Long postId, LikeStatus likeStatus) {
        var likeCount = postLikeCountRepository.findByPostIdAndLikeStatus(postId, likeStatus.getValue());
        if (likeCount.isPresent()) return likeCount.get().getCount();
        else return 0L;
    }

    public LikeCountDto countAll() {
        Map<LikeStatus, Long> count = new HashMap<>();

        for (var likeStatus: LikeStatus.values()) {
            count.put(likeStatus, findCountOfLikeStatus(likeStatus));
        }

        return LikeCountDto.builder()
                .count(count)
                .build();
    }

    public Long findCountOfLikeStatus(LikeStatus likeStatus) {
        Optional<GlobalLikeCount> likeCount = globalLikeCountRepository.findByLikeStatus(likeStatus.getValue());
        if (likeCount.isPresent()) return likeCount.get().getCount();
        else return 0L;
    }

    public void changeLikeCount(Long postId, LikeStatus likeStatus, Integer increment) {
        Optional<PostLikeCount> postLikeCount = postLikeCountRepository.findByPostIdAndLikeStatus(postId, likeStatus.getValue());
        Optional<GlobalLikeCount> globalLikeCount = globalLikeCountRepository.findByLikeStatus(likeStatus.getValue());

        // update the post like count repository
        if (postLikeCount.isPresent()) {
            postLikeCount.get().setCount(postLikeCount.get().getCount() + increment);
            postLikeCountRepository.save(postLikeCount.get());
        } else {
            postLikeCountRepository.save(PostLikeCount.builder()
                    .postId(postId)
                    .likeStatus(likeStatus.getValue())
                    .count(1L)
                    .build());
        }

        // update the global like repository
        if (globalLikeCount.isPresent()) {
            globalLikeCount.get().setCount(globalLikeCount.get().getCount()  + increment);
            globalLikeCountRepository.save(globalLikeCount.get());
        } else {
            globalLikeCountRepository.save(
                    GlobalLikeCount.builder()
                            .likeStatus(likeStatus.getValue())
                            .count(1L)
                            .build()
            );
        }


    }

}
