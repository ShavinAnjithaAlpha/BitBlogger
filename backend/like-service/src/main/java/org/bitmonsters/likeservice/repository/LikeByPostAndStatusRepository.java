package org.bitmonsters.likeservice.repository;

import org.bitmonsters.likeservice.model.LikeByPostAndStatus;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeByPostAndStatusRepository extends CassandraRepository<LikeByPostAndStatus, LikeByPostAndStatus.LikeKey> {

    Slice<LikeByPostAndStatus> findAllByKeyPostIdAndKeyLikeStatus(Long postId, Integer likeStatus, CassandraPageRequest cassandraPageRequest);

    void deleteByKeyPostIdAndKeyLikeStatusAndKeyUserId(Long postId, Integer likeStatus, Long userId);

    Long countAllByKeyPostIdAndKeyLikeStatus(Long postId, Integer likeStatus);
}
