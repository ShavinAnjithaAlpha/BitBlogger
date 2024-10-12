package org.bitmonsters.commentservice.repository;

import org.bitmonsters.commentservice.dto.CommentReactDto;
import org.bitmonsters.commentservice.model.CommentLike;
import org.bitmonsters.commentservice.model.LikeStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    Optional<CommentLike> findByCommentIdAndUserId(Long commentId, Long userId);

    Page<CommentLike> findAllByCommentId(Long commentId, Pageable page);

    Long countAllByCommentId(Long commentId);

    Long countAllByCommentIdAndLikeStatus(Long commentId, LikeStatus likeStatus);
}
