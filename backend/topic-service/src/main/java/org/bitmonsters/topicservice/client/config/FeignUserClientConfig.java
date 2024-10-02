package org.bitmonsters.topicservice.client.config;

import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class FeignUserClientConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new ErrorDecoder.Default();
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(100, 1000, 5);
    }
}
