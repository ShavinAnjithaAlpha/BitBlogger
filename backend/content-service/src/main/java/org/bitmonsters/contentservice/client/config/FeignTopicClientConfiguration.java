package org.bitmonsters.contentservice.client.config;

import feign.codec.ErrorDecoder;
import org.bitmonsters.contentservice.client.decoder.TopicErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignTopicClientConfiguration {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new TopicErrorDecoder();
    }

}
