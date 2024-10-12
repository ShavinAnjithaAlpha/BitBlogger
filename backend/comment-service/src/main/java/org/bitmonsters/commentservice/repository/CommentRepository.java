package org.bitmonsters.commentservice.repository;

import org.bitmonsters.commentservice.dto.CommentDto;
import org.bitmonsters.commentservice.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {


    Page<Comment> findAllByPostIdAndParentComment(String postId, Comment parentComment, Pageable page);

    Long countAllByPostId(String postId);

    Page<Comment> findAllByParentCommentId(Long commentId, Pageable page);
}
