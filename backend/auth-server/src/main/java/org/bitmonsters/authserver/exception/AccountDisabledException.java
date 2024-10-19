package org.bitmonsters.authserver.exception;

public class AccountDisabledException extends RuntimeException {
    public AccountDisabledException(String username) {
        super(username);
    }

    @Override
    public String getMessage() {
        return String.format("Account associated with email %s is disabled", super.getMessage());
    }
}
