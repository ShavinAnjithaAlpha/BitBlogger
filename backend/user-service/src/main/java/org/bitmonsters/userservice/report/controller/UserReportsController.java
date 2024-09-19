package org.bitmonsters.userservice.report.controller;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.userservice.report.dto.UserReportResponse;
import org.bitmonsters.userservice.report.service.UserReportService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users/reports")
@RequiredArgsConstructor
public class UserReportsController {

    private final UserReportService service;

    @GetMapping
    public Page<UserReportResponse> getUseReports(Pageable page) {
        return service.getUserReports(page);
    }

    @GetMapping("/{userId}")
    public Page<UserReportResponse> getUserReportByUserId(
            @PathVariable("userId") Long userId,
            Pageable page) {
        return service.getUserReportsByUserId(userId, page);
    }

}
