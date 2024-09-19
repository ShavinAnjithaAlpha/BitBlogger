package org.bitmonsters.likeservice.repository;

import org.bitmonsters.likeservice.model.LikeByUserAndStatus;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeByUserAndStatusRepository extends CassandraRepository<LikeByUserAndStatus, LikeByUserAndStatus.LikeKey> {

    Slice<LikeByUserAndStatus> findAllByKeyUserIdAndKeyLikeStatus(Long userId, Integer likeStatus, CassandraPageRequest cassandraPageRequest);

    void deleteByKeyUserIdAndKeyLikeStatusAndKeyPostId(Long userId, Integer likeStatus, Long postId);

    Slice<LikeByUserAndStatus> findAllByKeyUserId(Long userId, CassandraPageRequest cassandraPageRequest);

    Long countAllByKeyUserIdAndKeyLikeStatus(Long userId, Integer likeStatus);
}
