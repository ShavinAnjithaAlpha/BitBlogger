package org.bitmonsters.tagservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bitmonsters.tagservice.repository.PostTagRepository;
import org.bitmonsters.tagservice.repository.TagRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReconciliationJobs {

    private final TagRepository tagRepository;
    private final PostTagRepository postTagRepository;

    public void calculatePostCount() {
        log.info("[SCHEDULED] starting recalculating post counts job on tags");
        // get the post count on specific tags from the database
        // get all the tag store in the system
        var tags = tagRepository.findAll();
        // iterate through all the tags and find the post count through the querying post_tag table
        for (var tag: tags) {
            // get the count
            Long postCount = postTagRepository.countAllByTagId(tag.getId());
            log.info(String.format("tag %d: name: %s, count: %d", tag.getId(), tag.getName(), postCount));

            // set the post count
            tag.setPostCount(postCount);
            tagRepository.save(tag);
        }

        log.info("[SCHEDULED] finished the calculating post count job");
    }

}
