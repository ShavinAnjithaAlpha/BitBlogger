package org.bitmonsters.pollservice.client.decoder;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.bitmonsters.pollservice.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;

public class UserClientErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new ErrorDecoder.Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == HttpStatus.NOT_FOUND.value()) {
            return new UserNotFoundException("user is not found");
        }

        return errorDecoder.decode(methodKey, response);
    }
}
