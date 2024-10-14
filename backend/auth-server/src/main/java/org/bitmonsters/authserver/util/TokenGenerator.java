package org.bitmonsters.authserver.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TokenGenerator {

    private final Integer MAXIMUM_TOKEN_LENGTH = 20;
    private final SecureRandom secureRandom = new SecureRandom();

    public String generateRandomToken() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;

        return secureRandom.ints(leftLimit, rightLimit + 1)
                .filter(value -> (value <= 57 || value >= 65) && (value <= 90 || value >= 97))
                .limit(MAXIMUM_TOKEN_LENGTH)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
