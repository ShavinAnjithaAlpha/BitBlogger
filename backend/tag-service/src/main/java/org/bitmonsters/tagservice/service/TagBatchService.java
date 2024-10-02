package org.bitmonsters.tagservice.service;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.tagservice.model.Tag;
import org.bitmonsters.tagservice.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagBatchService {

    private final TagRepository tagRepository;
    private final TagMapper mapper;


    public List<?> getTagsAsBatch(List<Integer> tagIds, Boolean verbose) {
        if(verbose) {
            return tagRepository.findAllByIdIn(tagIds).stream()
                    .map(mapper::toTagDto)
                    .collect(Collectors.toList());
        } else {
            // return the tag name's based on tag id provided
            return tagRepository.findAllByIdIn(tagIds).stream()
                    .map(Tag::getName)
                    .collect(Collectors.toList());
        }
    }
}
