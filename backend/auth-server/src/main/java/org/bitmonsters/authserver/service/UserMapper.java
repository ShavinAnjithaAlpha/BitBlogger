package org.bitmonsters.authserver.service;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.authserver.dto.UserRegistrationRequest;
import org.bitmonsters.authserver.model.*;
import org.bitmonsters.authserver.util.PasswordUtil;
import org.bitmonsters.authserver.util.TokenGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final TokenGenerator tokenGenerator;
    private final PasswordUtil passwordUtil;


    public User toPendingUser(UserRegistrationRequest userRegistrationRequest) {
        return User.builder()
                .email(userRegistrationRequest.email())
                .status(AccountStatus.PENDING)
                .roles(List.of(Role.builder().id(1).build()))
                .locked(false)
                .hashingAlgorithm(HashingAlgorithm.BCRYPT)
                .passwordHash(passwordUtil.hashPassword(userRegistrationRequest.password()))
                .build();
    }

    public AuditLog toAuditLog(User user, EventType eventType, String details) {
        return AuditLog.builder()
                .user(user)
                .event(eventType)
                .details(details)
                .build();
    }

    public EmailVerificationToken toEmailVerificationToken(User user) {
        return EmailVerificationToken.builder()
                .user(user)
                .token(tokenGenerator.generateRandomToken())
                .expiresAt(LocalDateTime.now().plusHours(1))
                .build();
    }

    public PasswordResetToken toPasswordResetToken(User user) {
        return PasswordResetToken.builder()
                .user(user)
                .token(tokenGenerator.generateRandomToken())
                .expiresAt(LocalDateTime.now().plusHours(1))
                .build();
    }
}
