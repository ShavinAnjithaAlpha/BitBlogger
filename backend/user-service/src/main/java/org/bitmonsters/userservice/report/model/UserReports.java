package org.bitmonsters.userservice.report.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bitmonsters.userservice.report.UserReportReason;
import org.bitmonsters.userservice.user.model.User;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "USER_REPORTS")
public class UserReports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "reporter_id", nullable = false)
    private User reporter;

    @Column(name = "reason", length = 256)
    @Enumerated(value = EnumType.STRING)
    private UserReportReason reason;

    @Column(name = "note", length = 512)
    private String note;

    @CreatedDate
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
