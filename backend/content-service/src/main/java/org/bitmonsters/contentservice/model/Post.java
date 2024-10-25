package org.bitmonsters.contentservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
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

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(name = "cover_image", nullable = true)
    private String coverImage;

    @Column(nullable = false)
    private String preview;

    @Enumerated(EnumType.STRING)
    private PostStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "modified_at", insertable = false)
    @LastModifiedDate
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @Enumerated(EnumType.STRING)
    private Language language;

    @Column(name = "topic_id", nullable = false)
    private Integer topicId;

    @Embedded
    private PostMetaData metaData;


}
