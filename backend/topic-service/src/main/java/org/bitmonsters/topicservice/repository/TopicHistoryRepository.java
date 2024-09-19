package org.bitmonsters.topicservice.repository;

import org.bitmonsters.topicservice.model.TopicHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicHistoryRepository extends JpaRepository<TopicHistory, Integer> {

    List<TopicHistory> findAllByTopicId(Integer topicId);

}
