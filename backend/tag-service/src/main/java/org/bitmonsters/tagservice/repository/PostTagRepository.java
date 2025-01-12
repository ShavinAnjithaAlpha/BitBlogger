package org.bitmonsters.tagservice.repository;

import org.bitmonsters.tagservice.model.PostTag;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostTagRepository extends JpaRepository<PostTag, Long> {

    void deleteByPostIdAndTagId(String postId, Integer tagId);

    List<PostTag> findAllByPostId(String postId);

    Integer countAllByPostId(String postId);

    Slice<PostTag> findAllByTagId(Integer tagId);

    Optional<PostTag> findByTagIdAndPostId(Integer tagId, String postId);

    Long countAllByTagId(Integer tagId);
}
