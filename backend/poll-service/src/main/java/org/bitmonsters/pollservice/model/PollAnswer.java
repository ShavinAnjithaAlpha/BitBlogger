package org.bitmonsters.pollservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "poll_ans")
public class PollAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "poll_ans_id_seq")
    @SequenceGenerator(name = "poll_ans_id_seq", sequenceName = "poll_ans_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;

    @Column(name = "answer", nullable = false)
    private String answer;

    @Column(name = "ans_id", nullable = false)
    private Integer answerId;

    @Column(name = "answer_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AnswerStatus answerStatus;
}
