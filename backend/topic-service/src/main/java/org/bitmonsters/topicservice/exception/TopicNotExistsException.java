package org.bitmonsters.topicservice.exception;

public class TopicNotExistsException extends RuntimeException {
    public TopicNotExistsException(String message) {
        super(message);
    }
}
