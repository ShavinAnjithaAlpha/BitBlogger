package org.bitmonsters.authserver.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AuthException extends RuntimeException {

    private final HttpStatus status;
    private final String description;

    public AuthException(String message, HttpStatus status, String description) {
        super(message);
        this.status = status;
        this.description = description;
    }

    public AuthException(String message, String description) {
        this(message, HttpStatus.BAD_REQUEST, description);
    }

    public AuthException(String message) {
        this(message, null);
    }
}
