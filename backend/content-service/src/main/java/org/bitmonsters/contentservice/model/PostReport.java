package org.bitmonsters.contentservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "post_reports")
public class PostReport {

    @Id
    private String id;

    @DBRef
    private Post post;

    private Long reportedBy;

    private ReportReason reason;

    private String description;

    private ReportStatus status;

    private Long reviewedBy;

    private LocalDateTime reviewedAt;

    private String actionTaken;

    private ReportServerity serverity;

}
