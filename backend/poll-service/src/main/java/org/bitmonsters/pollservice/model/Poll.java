package org.bitmonsters.pollservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "polls")
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "poll_id_seq")
    @SequenceGenerator(name = "poll_id_seq", sequenceName = "poll_seq", allocationSize = 1)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "question", nullable = false)
    private String question;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "ends_at", nullable = false)
    private LocalDateTime endsAt;

    @Column(name = "photo", nullable = true)
    private String photo;

    @Column(name = "poll_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PollType type;

    @Column(nullable = true)
    @Embedded
    private PollMetaData metaData;

    @OneToMany(mappedBy = "poll")
    private List<PollTag> tags;

    @OneToMany(mappedBy = "poll")
    private List<PollAnswer> answers;

    @OneToMany(mappedBy = "poll")
    private List<PollAttempt> attempts;
}
