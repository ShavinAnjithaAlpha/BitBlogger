package org.bitmonsters.userservice.report.service;

import org.bitmonsters.userservice.me.dto.UserReportDto;
import org.bitmonsters.userservice.report.dto.UserInReport;
import org.bitmonsters.userservice.report.dto.UserReportResponse;
import org.bitmonsters.userservice.report.model.UserReports;
import org.bitmonsters.userservice.user.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserReportMapper {

    public UserReportResponse toUserReportResponse(UserReports userReports) {
        return UserReportResponse.builder()
                .id(userReports.getId())
                .note(userReports.getNote())
                .reason(userReports.getReason())
                .user(
                        UserInReport.builder()
                                .id(userReports.getUser().getId())
                                .username(userReports.getReporter().getUsername())
                                .name(userReports.getReporter().getName())
                                .email(userReports.getUser().getEmail())
                                .profileImage(userReports.getUser().getProfileImage())
                                .build()
                )
                .reporter(
                        UserInReport.builder()
                                .id(userReports.getReporter().getId())
                                .username(userReports.getReporter().getUsername())
                                .name(userReports.getReporter().getName())
                                .email(userReports.getReporter().getEmail())
                                .profileImage(userReports.getUser().getProfileImage())
                                .build()
                )
                .createdAt(userReports.getCreatedAt())
                .build();
    }

    public UserReports toUserReport(Long userId, UserReportDto reportDto) {
        return UserReports.builder()
                .user(User.builder().id(reportDto.userId()).build())
                .reporter(User.builder().id(userId).build())
                .reason(reportDto.reason())
                .note(reportDto.note())
                .build();
    }
}
