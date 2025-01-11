package org.bitmonsters.contentservice.repository;

import org.bitmonsters.contentservice.dto.PostDto;
import org.bitmonsters.contentservice.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends MongoRepository<Post, String> {

    Page<Post> findByUserId(Long userId, Pageable page);

    Page<Post> findAllByDeleted(Boolean deleted, Pageable page);

    Page<Post> findByUserIdAndDeleted(Long userId, Boolean deleted, Pageable page);

    Page<Post> findAllByTopicId(Integer topicId, Pageable page);
}
