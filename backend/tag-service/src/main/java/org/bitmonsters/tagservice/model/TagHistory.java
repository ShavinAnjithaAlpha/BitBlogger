package org.bitmonsters.tagservice.model;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tag_history")
public class TagHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false, updatable = false)
    private Tag tag;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "description", length = 1024, nullable = false)
    private String description;

    @Column(name = "icon", length = 1024)
    private String icon;

    @Enumerated(EnumType.STRING)
    @Column(name = "action", nullable = false)
    private TagAction action;

    @Column(name = "changed_by", nullable = false)
    private Long changedBy;

    @Column(name = "changed_at", nullable = false, updatable = false)
    @CreatedDate
    @CreationTimestamp
    private LocalDateTime changedAt;
}
