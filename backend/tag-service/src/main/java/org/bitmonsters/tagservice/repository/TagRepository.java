package org.bitmonsters.tagservice.repository;

import org.bitmonsters.tagservice.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    List<Tag> findAllByIdIn(List<Integer> tagIds);

    Optional<Tag> findByName(String name);
}
