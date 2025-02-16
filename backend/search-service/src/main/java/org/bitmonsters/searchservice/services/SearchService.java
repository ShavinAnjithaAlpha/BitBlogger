package org.bitmonsters.searchservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.lucene.search.join.ScoreMode;
import org.bitmonsters.searchservice.exception.PostNotFoundException;
import org.bitmonsters.searchservice.model.Post;
import org.bitmonsters.searchservice.repositories.PostRepository;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private final RestHighLevelClient restHighLevelClient;
    private final ObjectMapper objectMapper;
    private final PostRepository postRepository;

    public SearchService(RestHighLevelClient client, ObjectMapper mapper, PostRepository postRepository) {
        this.restHighLevelClient = client;
        this.objectMapper = mapper;
        this.postRepository = postRepository;
    }

    public List<Post> advancedSearch(String query, Integer page, Integer size) throws Exception {
        SearchRequest searchRequest = new SearchRequest("blog");

        int from  = (page) * size; // pagination starting index

        // multi field search query with boosting
        MultiMatchQueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(query)
                .field("title", 3.0f)
                .field("content", 2.0f)
                .field("preview", 1.5f)
                .field("topic.name", 2.5f) // boosting topic name;
                .field("topic.desc", 1.8f) // boosting topic description
                .field("tags.name", 2.0f); // boosting tags name

        // nested query for topic and tags
        NestedQueryBuilder topicQueryBuilder = QueryBuilders.nestedQuery(
                "topic",
                QueryBuilders.matchQuery("topic.name", query),
                ScoreMode.Avg
        );

        NestedQueryBuilder tagsQueryBuilder = QueryBuilders.nestedQuery(
                "tags",
                QueryBuilders.matchQuery("tags.name", query),
                ScoreMode.Avg
        );

        // build a boolean query to combine multiple queries
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
                .should(queryBuilder)
                .should(topicQueryBuilder)
                .should(tagsQueryBuilder)
                .boost(1.0f);

        // sort by timestamp (more recent posts appear first)
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
                .query(boolQuery)
                .from(from)
                .size(size)
                .sort("createdAt", SortOrder.DESC);

        searchRequest.source(searchSourceBuilder);

        // execute the search query
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        // convert response to Post objects
        List<Post> posts = new ArrayList<>();
        response.getHits().forEach(hit -> {
            try {
                posts.add(objectMapper.readValue(hit.getSourceAsString(), Post.class));
                System.out.println(hit.getSourceAsString());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        return posts;
    }

    public Post getPostById(String id) {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(String.format("post with id %s is not found", id)));
    }

    public List<Post> findPostsByAuthor(String authorName) {
        return postRepository.findByAuthor(authorName);
    }

    public Page<Post> findPostsByTopic(String topicName, Pageable pageable) {
        return postRepository.findByTopicName(topicName, pageable);
    }

    public Page<Post> findPostsByTags(String tagName, Pageable pageable) {
        return postRepository.findByTagName(tagName, pageable);
    }

}
