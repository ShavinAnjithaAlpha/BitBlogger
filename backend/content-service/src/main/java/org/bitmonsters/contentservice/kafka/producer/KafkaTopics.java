package org.bitmonsters.contentservice.kafka.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopics {

    @Bean
    public NewTopic newPostTopic() {
        return TopicBuilder.name("NEW-POST").build();
    }

    @Bean
    public NewTopic deletePostTopic() {
        return TopicBuilder.name("DELETE-POST").build();
    }

    @Bean
    public NewTopic incrementPostCountTopic() {
        return TopicBuilder.name("POST-COUNT-INCREMENT").build();
    }

}
