package org.bitmonsters.topicservice.exception;

public class TopicAlreadyExistsException extends RuntimeException {
    public TopicAlreadyExistsException(String message) {
        super(message);
    }
}
