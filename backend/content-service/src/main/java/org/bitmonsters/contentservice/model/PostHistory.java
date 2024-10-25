package org.bitmonsters.contentservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "post_history")
public class PostHistory {

    @Id
    private String id;

    @Column(name = "post_id", nullable = false)
    private String postId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer topicId;

    private String version;

    @Column(name = "modified_at", nullable = false, updatable = false)
    @CreatedDate
    @CreationTimestamp
    private LocalDateTime modifiedAt;

    @Column(name = "action", nullable = false)
    @Enumerated(EnumType.STRING)
    private PostAction action;

    @Column(name = "changed_summary", nullable = true)
    private String changedSummary;

}
