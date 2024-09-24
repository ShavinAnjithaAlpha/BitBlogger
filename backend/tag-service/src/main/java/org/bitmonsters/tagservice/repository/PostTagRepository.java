package org.bitmonsters.tagservice.repository;

import org.bitmonsters.tagservice.model.PostTag;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostTagRepository extends JpaRepository<PostTag, Long> {

    void deleteByPostIdAndTagId(Long postId, Integer tagId);

    List<PostTag> findAllByPostId(Long postId);

    Integer countAllByPostId(Long postId);

    Slice<PostTag> findAllByTagId(Integer tagId);

    Optional<PostTag> findByTagIdAndPostId(Integer tagId, Long postId);
}
