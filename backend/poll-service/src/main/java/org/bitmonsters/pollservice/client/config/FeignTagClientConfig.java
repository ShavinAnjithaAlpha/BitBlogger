package org.bitmonsters.pollservice.client.config;

import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.bitmonsters.pollservice.client.decoder.TagClientErrorDecoder;
import org.springframework.context.annotation.Bean;

public class FeignTagClientConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new TagClientErrorDecoder();
    }

    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100, 1000, 5);
    }
}
