package org.bitmonsters.mediaservice.model;

import lombok.Getter;

@Getter
public enum ContentType {
    USER("USER"),
    POST("POST");

    private final String contentType;

    ContentType(String contentType) {
        this.contentType = contentType;
    }
}
