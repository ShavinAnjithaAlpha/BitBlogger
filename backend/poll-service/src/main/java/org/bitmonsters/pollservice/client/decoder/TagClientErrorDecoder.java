package org.bitmonsters.pollservice.client.decoder;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.bitmonsters.pollservice.exception.TagNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class TagClientErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == HttpStatus.NOT_FOUND.value()) {
            // return custom tag not found exception when 404 errors
            return new TagNotFoundException("tag is not found");
        }
        // otherwise use the default error decoder
        return defaultDecoder.decode(methodKey, response);
    }
}
