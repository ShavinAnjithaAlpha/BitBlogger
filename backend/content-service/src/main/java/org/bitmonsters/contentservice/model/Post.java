package org.bitmonsters.contentservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "posts")
public class Post {

    @Id
    private String id;

    private Long userId;

    private String title;

    private String content;

    private String coverImage;

    private String preview;

    private PostStatus status;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    private LocalDateTime publishedAt;

    private Language language;

    private Integer topicId;

    private PostMetaData metaData;

    private Boolean deleted;
}
