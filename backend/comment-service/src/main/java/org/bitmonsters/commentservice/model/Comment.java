package org.bitmonsters.commentservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id_gen")
    @SequenceGenerator(name = "comment_id_gen", sequenceName = "comments_seq", allocationSize = 1)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "post_id", nullable = false)
    private String postId;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "commented_at", nullable = false, updatable = false)
    private LocalDateTime commentedAt;

    @Column(name = "modified_at", insertable = false)
    private LocalDateTime modifiedAt;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = true)
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replies;

    @OneToMany(mappedBy = "comment")
    private List<CommentLike> likes;
}
