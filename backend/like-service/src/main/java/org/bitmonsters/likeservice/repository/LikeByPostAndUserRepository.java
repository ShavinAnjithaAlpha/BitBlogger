package org.bitmonsters.likeservice.repository;

import org.bitmonsters.likeservice.model.LikeByPostAndUser;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeByPostAndUserRepository extends CassandraRepository<LikeByPostAndUser, LikeByPostAndUser.LikeKey> {

    Optional<LikeByPostAndUser> findByKeyPostIdAndKeyUserId(Long postId, Long userId);

    Slice<LikeByPostAndUser> findAllByKeyPostId(Long postId, CassandraPageRequest cassandraPageRequest);

    void deleteByKeyPostIdAndKeyUserId(Long postId, Long userId);
}
