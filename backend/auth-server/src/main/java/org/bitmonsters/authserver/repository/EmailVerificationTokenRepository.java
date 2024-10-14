package org.bitmonsters.authserver.repository;

import org.bitmonsters.authserver.model.EmailVerificationToken;
import org.bitmonsters.authserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken, Long> {
    Optional<EmailVerificationToken> findByUser(User user);
}
