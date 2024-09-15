package org.bitmonsters.userservice.report.service;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.userservice.report.dto.UserReportResponse;
import org.bitmonsters.userservice.report.repository.UserReportsRepository;
import org.bitmonsters.userservice.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserReportService {

    private final UserReportsRepository reportsRepository;
    private final UserReportMapper mapper;

    public Page<UserReportResponse> getUserReports(Pageable page) {
        return reportsRepository.findAll(page)
                .map(mapper::toUserReportResponse);
    }

    public Page<UserReportResponse> getUserReportsByUserId(Long userId, Pageable page) {
        return reportsRepository.findAllByUser(User.builder().id(userId).build(), page)
                .map(mapper::toUserReportResponse);
    }
}
