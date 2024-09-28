package org.bitmonsters.pollservice.client.config;

import feign.codec.ErrorDecoder;
import org.bitmonsters.pollservice.client.decoder.TagClientErrorDecoder;
import org.springframework.context.annotation.Bean;

public class FeignTagClientConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new TagClientErrorDecoder();
    }
}
