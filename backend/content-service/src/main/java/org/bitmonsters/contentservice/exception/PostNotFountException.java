package org.bitmonsters.contentservice.exception;

public class PostNotFountException extends RuntimeException {
    public PostNotFountException(String postId) {
        super(String.format("Post does not exists: %s", postId));
    }
}
