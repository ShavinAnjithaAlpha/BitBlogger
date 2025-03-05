package org.bitmonsters.contentservice.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bitmonsters.contentservice.client.feign.TagDto;
import org.bitmonsters.contentservice.client.feign.TopicDto;
import org.bitmonsters.contentservice.client.feign.UserResponse;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewPostBrokerDto {
    String id;
    UserResponse author;
    String title;
    String content;
    String preview;
    LocalDateTime publishedAt;
    String language;
    TopicDto topic;
    List<TagDto> tags;
}
