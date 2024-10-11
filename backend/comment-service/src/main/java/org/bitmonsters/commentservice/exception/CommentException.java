package org.bitmonsters.commentservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommentException extends RuntimeException {

    private final HttpStatus status;

    public CommentException(String message, HttpStatus httpStatus) {
        super(message);
        this.status = httpStatus;
    }

}
