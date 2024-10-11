package org.bitmonsters.commentservice.service;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.commentservice.dto.CommentReactDto;
import org.bitmonsters.commentservice.dto.CommentReactionStatDto;
import org.bitmonsters.commentservice.dto.NewCommentReactDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentReactionService {


    public void reactToComment(Long commentId, Long userId, NewCommentReactDto newCommentReactDto) {

    }

    public void removeReact(Long commentId, Long userId) {

    }

    public Page<CommentReactDto> getReactsToComment(Long commentId, Pageable page) {
        return null;
    }

    public Boolean isReactToComment(Long commentId, Long userId) {
        return null;
    }

    public void updateCommentReact(Long commentId, Long userId, NewCommentReactDto newCommentReactDto) {

    }

    public Long getReactionCountOfComment(Long commentId) {
        return null;
    }

    public CommentReactionStatDto getCommentReactionStats(Long commentId) {
        return null;
    }
}
