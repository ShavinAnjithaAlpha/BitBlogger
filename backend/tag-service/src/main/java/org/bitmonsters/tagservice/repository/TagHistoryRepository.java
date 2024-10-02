package org.bitmonsters.tagservice.repository;

import org.bitmonsters.tagservice.dto.TagHistoryDto;
import org.bitmonsters.tagservice.model.TagHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagHistoryRepository extends JpaRepository<TagHistory, Integer> {
    List<TagHistory> findAllByTagId(Integer tagId);
}
