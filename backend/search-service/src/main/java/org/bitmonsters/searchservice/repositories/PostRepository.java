package org.bitmonsters.searchservice.repositories;

import org.bitmonsters.searchservice.model.Post;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PostRepository extends ElasticsearchRepository<Post, String> {
}
