package org.bitmonsters.commentservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommentLikeException extends RuntimeException {

    private final HttpStatus status;

    public CommentLikeException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public CommentLikeException(String message) {
        this(message, HttpStatus.BAD_REQUEST);
    }
}
