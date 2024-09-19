package org.bitmonsters.likeservice.repository;

import org.bitmonsters.likeservice.model.GlobalLikeCount;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GlobalLikeCountRepository extends CassandraRepository<GlobalLikeCount, Integer> {

    Optional<GlobalLikeCount> findByLikeStatus(Integer likeStatus);
}
