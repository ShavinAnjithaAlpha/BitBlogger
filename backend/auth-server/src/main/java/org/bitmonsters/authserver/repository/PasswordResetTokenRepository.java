package org.bitmonsters.authserver.repository;

import org.bitmonsters.authserver.model.PasswordResetToken;
import org.bitmonsters.authserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByUser(User user);
}
