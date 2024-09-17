package org.bitmonsters.likeservice.service;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.likeservice.dto.CassandraPage;
import org.bitmonsters.likeservice.dto.PostLikeDto;
import org.bitmonsters.likeservice.dto.UserLikeDto;
import org.bitmonsters.likeservice.model.LikeByPostAndUser;
import org.bitmonsters.likeservice.model.LikeStatus;
import org.bitmonsters.likeservice.repository.LikeByPostAndStatusRepository;
import org.bitmonsters.likeservice.repository.LikeByPostAndUserRepository;
import org.bitmonsters.likeservice.repository.LikeByUserAndStatusRepository;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeByPostAndUserRepository likeByPostAndUserRepository;
    private final LikeByPostAndStatusRepository likeByPostAndStatusRepository;
    private final LikeByUserAndStatusRepository likeByUserAndStatusRepository;
    private final LikeMapper mapper;

    public void likePost(Long postId, Long userId, LikeStatus likeStatus) {
        Instant likedAt = Instant.now();
        likeByPostAndUserRepository.save(mapper.toLikeByPostAndUser(postId, userId, likeStatus, likedAt));
        likeByPostAndStatusRepository.save(mapper.toLikeByPostAndStatus(postId, userId, likeStatus, likedAt));
        likeByUserAndStatusRepository.save(mapper.toLikeByUserAndStatus(postId, userId, likeStatus, likedAt));
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
}
