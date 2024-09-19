package org.bitmonsters.topicservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "topic_history")
public class TopicHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "parent_topic", nullable = true)
    private Integer parentTopicId;

    @Column(name = "changed_by", nullable = false)
    private Long changedBy;

    @Column(name = "changed_at", updatable = false, nullable = false)
    @CreatedDate
    @CreationTimestamp
    private LocalDateTime changedAt;

    @Column(name = "action", nullable = false)
    @Enumerated(EnumType.STRING)
    private TopicAction action;

}
