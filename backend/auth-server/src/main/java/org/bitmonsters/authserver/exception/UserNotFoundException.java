package org.bitmonsters.authserver.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends AuthException {
    public UserNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND, null);
    }
}
