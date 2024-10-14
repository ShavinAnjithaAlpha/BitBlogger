package org.bitmonsters.authserver.model;

public enum EventType {
    LOGIN,
    LOGOUT,
    LOGIN_FAILURE,
    PASSWORD_RESET,
    USER_CREATED,
    PASSWORD_RESET_REQUEST, EMAIL_VERIFIED
}
