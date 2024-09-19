package org.bitmonsters.likeservice.repository;

import org.bitmonsters.likeservice.model.PostLikeCount;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostLikeCountRepository extends CassandraRepository<PostLikeCount, Long> {

    Optional<PostLikeCount> findByPostIdAndLikeStatus(Long postId, Integer likeStatus);

}
