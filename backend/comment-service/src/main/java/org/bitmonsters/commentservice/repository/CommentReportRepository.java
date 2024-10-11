package org.bitmonsters.commentservice.repository;

import org.bitmonsters.commentservice.model.CommentReport;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentReportRepository extends JpaRepository<CommentReport, Long> {

    @Query("SELECT r.comment, COUNT(r.id) FROM CommentReport AS r GROUP BY r.comment")
    Slice<Object[]> getDistinctReportsByComments(Pageable page);

    Slice<CommentReport> findAllByCommentId(Long commentId, Pageable page);
}
