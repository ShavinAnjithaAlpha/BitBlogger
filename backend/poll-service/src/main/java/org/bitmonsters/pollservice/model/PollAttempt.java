package org.bitmonsters.pollservice.model;

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
@Table(name = "poll_attempts")
public class PollAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "poll_attempt_id_seq")
    @SequenceGenerator(name = "poll_attempt_id_seq", sequenceName = "poll_attempts_seq", allocationSize = 1)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;

    @Column(name = "ans_id", nullable = true)
    private Integer answerId;

    @Column(name = "answered_at", nullable = false, updatable = false)
    @CreatedDate
    @CreationTimestamp
    private LocalDateTime answeredAt;

    @Column(name = "public", nullable = false)
    private Boolean isPublic;

    @Column(name = "optional_answer", nullable = true)
    private String optionalAnswer;


}
