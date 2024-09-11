package org.bitmonsters.userservice.report;

import org.bitmonsters.userservice.user.User;
import org.bitmonsters.userservice.user.UserReports;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReportsRepository extends JpaRepository<UserReports, Long> {

    Page<UserReports> findAllByReporter(User reporter, Pageable pageable);
}
