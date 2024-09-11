package org.bitmonsters.userservice.report;

import org.bitmonsters.userservice.user.UserReports;
import org.springframework.stereotype.Service;

@Service
public class UserReportMapper {

    public UserReportResponse toUserReportResponse(UserReports userReports) {
        return UserReportResponse.builder()
                .id(userReports.getId())
                .note(userReports.getNote())
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
}
