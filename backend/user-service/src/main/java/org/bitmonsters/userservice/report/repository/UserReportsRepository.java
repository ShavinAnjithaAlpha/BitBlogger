package org.bitmonsters.userservice.report.repository;

import org.bitmonsters.userservice.report.model.UserReports;
import org.bitmonsters.userservice.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserReportsRepository extends JpaRepository<UserReports, Long> {

    Page<UserReports> findAllByUser(User reporter, Pageable pageable);

    Optional<UserReports> findByUserIdAndReporterId(Long userId, Long reporterId);
}
