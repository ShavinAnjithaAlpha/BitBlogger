package org.bitmonsters.contentservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
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
@Document(value = "draft")
public class PostDraft {

    @Id
    private String id;

    private String title;

    private String content;

    @Column(name = "cover_image")
    private String coverImage;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "modified_at", insertable = false)
    @LastModifiedDate
    @UpdateTimestamp
    private LocalDateTime modifiedAt;
}
