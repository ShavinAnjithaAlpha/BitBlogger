package org.bitmonsters.userservice.exception;

public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException(String message) {
        super(message);
    }
}
