package org.bitmonsters.tagservice.exception;

public class TagAlreadyExistsException extends RuntimeException {
    public TagAlreadyExistsException(String message) {
        super(message);
    }
}
