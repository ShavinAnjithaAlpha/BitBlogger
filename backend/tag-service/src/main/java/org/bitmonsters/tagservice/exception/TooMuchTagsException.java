package org.bitmonsters.tagservice.exception;

import org.bitmonsters.tagservice.service.TagService;

public class TooMuchTagsException extends RuntimeException {

    private String postId;
    private Integer currentTagCount;
    private Integer tagCount;

    public TooMuchTagsException(String postId, Integer tagCount, int size) {
        super();
        this.postId = postId;
        this.currentTagCount = tagCount;
        this.tagCount = size;
    }

    @Override
    public String getMessage() {
        return String.format("too much tags for post with id %s: current tag count: %d, provided tag count: %d, maximum allowed: %d",
                postId, currentTagCount, tagCount, TagService.MAXIMUM_TAG_PER_POST);
    }
}
