package org.bitmonsters.commentservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "comment_likes")
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_likes_gen")
    @SequenceGenerator(name = "comment_likes_gen", sequenceName = "comment_likes_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "liked_at", updatable = false)
    private LocalDateTime likedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LikeStatus likeStatus;
}
