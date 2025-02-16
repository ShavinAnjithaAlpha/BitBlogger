package org.bitmonsters.searchservice.repositories;

import org.bitmonsters.searchservice.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends ElasticsearchRepository<Post, String> {

    @Query("{\"query\": {\"bool\": {\"should\": [{\"match\": {\"author.name\": {\"query\": \"?0\", \"fuzziness\": \"AUTO\"}}}, {\"match\": {\"author.username\": {\"query\": \"?0\", \"fuzziness\": \"AUTO\"}}}], \"minimum_should_match\": 1}}}")
    List<Post> findByAuthor(String author);

    Page<Post> findByTitle(String title, Pageable pageable);

    @Query("{\"query\": {\"bool\": {\"should\": [{\"match\": {\"tags.name\": {\"query\": \"?0\", \"fuzziness\": \"AUTO\"}}}, {\"match\": {\"tags.desc\": {\"query\": \"?0\", \"fuzziness\": \"AUTO\"}}}], \"minimum_should_match\": 1}}}")
    Page<Post> findByTagName(String tagName, Pageable pageable);

    @Query("{\"query\": {\"match\": {\"topic.name\": \"?0\"}}}")
    Page<Post> findByTopicName(String topicName, Pageable pageable);



}
