package org.bitmonsters.pollservice.client.config;

import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.bitmonsters.pollservice.client.decoder.UserClientErrorDecoder;
import org.springframework.context.annotation.Bean;

public class FeignUserClientConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new UserClientErrorDecoder();
    }

    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100, 1000, 5);
    }

}
