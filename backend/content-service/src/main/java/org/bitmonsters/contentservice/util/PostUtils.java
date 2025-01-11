package org.bitmonsters.contentservice.util;

import org.springframework.stereotype.Service;

@Service
public class PostUtils {

    private final int READING_FACTOR = 200;

    public int readingTime(String content) {
        return content.split(" ").length / READING_FACTOR;
    }

}
