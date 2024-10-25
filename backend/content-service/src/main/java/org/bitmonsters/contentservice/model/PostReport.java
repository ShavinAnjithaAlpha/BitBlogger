package org.bitmonsters.contentservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "post_reports")
public class PostReport {

    @Id
    private String id;

    private String postId;

    @Column(name = "reported_by", nullable = false)
    private Long reportedBy;

    @Column(name = "report_reason", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReportReason reason;

    private String description;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    @Column(name = "reviewed_by")
    private Long reviewedBy;

    @Column(name = "reviewed_at")
    private LocalDateTime reviewedAt;

    @Column(name = "action_taken")
    private String actionTaken;

    private ReportServerity serverity;

}
