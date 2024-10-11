package org.bitmonsters.commentservice.service;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.commentservice.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    public IDResponse addComment(String postId, Long userId, NewCommentDto newCommentDto) {
        return null;
    }

    public Page<CommentDto> getCommentOfPost(String postId, Pageable page) {
        return null;
    }

    public Long getCommentCountOfPost(String postId) {
        return null;
    }


    public CommentDto getCommentById(Long commentId) {
        return null;
    }

    public void updateComment(Long commentId, Long userId) {

    }

    public void deleteComment(Long commentId, Long userId) {

    }

    public IDResponse addReply(Long commentId, Long userId, NewCommentDto newCommentDto) {
        return null;
    }

    public Page<CommentDto> getRepliesOfComment(Long commentId) {
        return null;
    }

    public void reportComment(Long commentId, Long userId, NewCommentReportDto newCommentReportDto) {

    }

    public CommentReportDto getReportsOnComment(Pageable page) {
        return null;
    }

    public CommentReportDto getReportsByComment(Long commentId) {
        return null;
    }
}
