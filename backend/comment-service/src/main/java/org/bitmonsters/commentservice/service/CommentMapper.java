package org.bitmonsters.commentservice.service;

import org.bitmonsters.commentservice.dto.CommentDto;
import org.bitmonsters.commentservice.dto.CommentReportDto;
import org.bitmonsters.commentservice.dto.CommentWithReportsDto;
import org.bitmonsters.commentservice.dto.NewCommentReportDto;
import org.bitmonsters.commentservice.model.Comment;
import org.bitmonsters.commentservice.model.CommentReport;
import org.springframework.stereotype.Service;

@Service
public class CommentMapper {

    public Comment toComment(String postId, Long userId, String content) {
        return Comment.builder()
                .postId(postId)
                .userId(userId)
                .content(content)
                .build();
    }

    public CommentDto toCommentDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .user(comment.getUserId())
                .content(comment.getContent())
                .commentedAt(comment.getCommentedAt())
                .modifiedAt(comment.getModifiedAt())
                .replyCount(comment.getReplies().size())
                .reactionCount(comment.getLikes().size())
                .build();
    }

    public Comment toReply(Comment comment, Long userId, String sanitizedContent) {
        return Comment.builder()
                .postId(comment.getPostId())
                .userId(userId)
                .content(sanitizedContent)
                .parentComment(comment)
                .build();
    }

    public CommentReport toCommentReport(Comment comment, Long userId, NewCommentReportDto newCommentReportDto) {
        return CommentReport.builder()
                .comment(comment)
                .userId(userId)
                .reportStatus(newCommentReportDto.reason())
                .otherInfo(newCommentReportDto.otherInfo())
                .build();
    }

    public CommentWithReportsDto.CommentWithReportsRecord toCommentWithReportsRecord(Object[] objects) {
        return CommentWithReportsDto.CommentWithReportsRecord.builder()
                .comment(this.toCommentDto((Comment) objects[0]))
                .numberOfReports((Long) objects[1])
                .build();
    }

    public CommentReportDto toCommentReportDto(CommentReport commentReport) {
        return CommentReportDto.builder()
                .id(commentReport.getId())
                .user(commentReport.getUserId())
                .reason(commentReport.getReportStatus())
                .otherInfo(commentReport.getOtherInfo())
                .reportedAt(commentReport.getReportedAt())
                .build();
    }
}
