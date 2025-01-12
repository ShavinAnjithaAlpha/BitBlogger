package org.bitmonsters.contentservice.client.decoder;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.bitmonsters.contentservice.client.exception.TopicNotFoundException;

public class TopicErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 404) {
            return new TopicNotFoundException("topic not found: ");
        }

        return new Exception("Error occured: " + response.status());
    }
}
