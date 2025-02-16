package org.bitmonsters.searchservice.controller;

import jakarta.ws.rs.QueryParam;
import lombok.RequiredArgsConstructor;
import org.bitmonsters.searchservice.model.Post;
import org.bitmonsters.searchservice.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Post> searchPosts(@QueryParam("query") String query, Pageable pageable) throws Exception {
        return searchService.advancedSearch(query, pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/post/{postId}")
    public Post getPostById(@PathVariable("postId") String postId) {
        return searchService.getPostById(postId);
    }

    @GetMapping("/author")
    public List<Post> getPostsByAuthor(@QueryParam("name") String name) {
        return searchService.findPostsByAuthor(name);
    }

    @GetMapping("/topic")
    public Page<Post> getPostsByTopic(@QueryParam("name") String name, Pageable pageable) {
        return searchService.findPostsByTopic(name, pageable);
    }

    @GetMapping("/tags")
    public Page<Post> getPostsByTags(@QueryParam("name") String name, Pageable pageable) {
        return searchService.findPostsByTags(name, pageable);
    }

}