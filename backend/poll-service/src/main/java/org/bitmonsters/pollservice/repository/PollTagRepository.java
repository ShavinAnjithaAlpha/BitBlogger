package org.bitmonsters.pollservice.repository;

import org.bitmonsters.pollservice.model.PollTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollTagRepository extends JpaRepository<PollTag, Long> {
}
