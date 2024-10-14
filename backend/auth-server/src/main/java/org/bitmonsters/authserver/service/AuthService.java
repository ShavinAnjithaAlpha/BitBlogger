package org.bitmonsters.authserver.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bitmonsters.authserver.dto.IDResponse;
import org.bitmonsters.authserver.dto.PasswordResetRequest;
import org.bitmonsters.authserver.dto.UserRegistrationRequest;
import org.bitmonsters.authserver.exception.AuthException;
import org.bitmonsters.authserver.exception.UserAlreadyExistsException;
import org.bitmonsters.authserver.exception.UserNotFoundException;
import org.bitmonsters.authserver.model.AccountStatus;
import org.bitmonsters.authserver.model.EventType;
import org.bitmonsters.authserver.model.PasswordResetToken;
import org.bitmonsters.authserver.repository.*;
import org.bitmonsters.authserver.util.PasswordUtil;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountLockedException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final EmailVerificationTokenRepository emailVerificationTokenRepository;
    private final RoleRepository roleRepository;
    private final UserMapper mapper;
    private final PasswordUtil passwordUtil;
    private final AuditLogRepository auditLogRepository;
    private final MailTrapEmailServiceImpl emailService;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    public boolean isUserExists(String username, String email) {
        boolean userExistsByEmail = userRepository.findByEmail(email).isPresent();
        boolean userExistsByUsername = false;

        return userExistsByEmail || userExistsByUsername;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void registerUser(UserRegistrationRequest userRegistrationRequest) {
        // create a user record in the database with email verification pending status
        // default user role is GENERAL USER
        var user = userRepository.save(mapper.toPendingUser(userRegistrationRequest));

        if (isUserExists(userRegistrationRequest.username(), userRegistrationRequest.email())) {
            throw new UserAlreadyExistsException(
                    String.format("user with username %s or email %s is already registered",
                    userRegistrationRequest.name(), userRegistrationRequest.email()));
        }

        // generate an email verification code for the verification process
        var emailVerificationToken = emailVerificationTokenRepository.save(
                mapper.toEmailVerificationToken(user)
        );

        // send an email ot verify the user's identity
        emailService.sendVerificationEmail(user.getEmail(), emailVerificationToken);

        // audit the user registration process in the audit log
        auditLogRepository.save(mapper.toAuditLog(
                user,
                EventType.USER_CREATED,
                String.format("user created with the email %s and username %s", userRegistrationRequest.email(), userRegistrationRequest.username())
                )
        );
    }

    public IDResponse confirmEmail(String email, String code) {
        // find the user associated with the given email
        var user = userRepository.findByEmail(email).orElseThrow(
                () -> new AuthException(String.format("user with email id %s didn't request registration", email))
        );

        // fetch the email verification record from the database
        var emailTokenRecord = emailVerificationTokenRepository.findByUser(user).orElseThrow(
                () -> new AuthException(String.format("email verification request is expired: %s", email))
        );

        // check whether if the email verification link is expired
        if (emailTokenRecord.getExpiresAt().isBefore(LocalDateTime.now())) {
            // delete the email verification code record
            emailVerificationTokenRepository.delete(emailTokenRecord);
            throw new AuthException(String.format("email verification link is expired: %s", email));
        }

        // get the token and try to match the code
        if (code.equals(emailTokenRecord.getToken())) {
             // finish the registration of the user in the auth server
            // delete the record of the email token record
            emailVerificationTokenRepository.delete(emailTokenRecord);

            // change the status of the user account to the active
            user.setStatus(AccountStatus.ACTIVE);
            userRepository.save(user);

            // create an audit log about email verification
            auditLogRepository.save(mapper.toAuditLog(user, EventType.EMAIL_VERIFIED, String.format("account is verified of the user with email %s", email)));

            // return the id iof the registered user
            return new IDResponse(user.getId());
        } else {
            throw new AuthException(String.format(String.format("Bad verification code: %s", email)));
        }
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void resetPassword(PasswordResetRequest passwordResetRequest) throws AccountLockedException {
        // first check whether if the email associated with a user account in the system
        var user = userRepository.findByEmail(passwordResetRequest.email());
        if (user.isEmpty())
            throw new UserNotFoundException(String.format("user with email %s is not found", passwordResetRequest.email()));

        // check whether if the account is locked
        if (user.get().getLocked())
            throw new AccountLockedException(String.format("account locked: %s", user.get().getEmail()));

        // generate a password reset token
        PasswordResetToken passwordToken = passwordResetTokenRepository.save(mapper.toPasswordResetToken(user.get()));

        // if the user exists send the reset email
        emailService.sendPasswordResetEmail(passwordResetRequest.email(), passwordToken);

        // create an audit log of resetting the password
        auditLogRepository.save(mapper.toAuditLog(user.get(), EventType.PASSWORD_RESET_REQUEST, String.format("user with email %s is request a password reset", passwordResetRequest.email())));
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void changePassword(String email, String code, String newPassword) {
        // first check whether if the user exists in the system
        var user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format("user with email %s is not found", email));
        }

        // otherwise check the password reset code and validate it
        var passwordResetRecord = passwordResetTokenRepository.findByUser(user.get());
        if (passwordResetRecord.isEmpty())
            throw new AuthException(String.format("password reset token is not found: %s", email));

        // match the password reset token
        if (!code.equals(passwordResetRecord.get().getToken()))
            throw new AuthException(String.format("password reset token is invalid: %s", email));

        // check whether password reset token is expired or not
        if (passwordResetRecord.get().getExpiresAt().isBefore(LocalDateTime.now())) {
            // delete the password reset token record from the database
            passwordResetTokenRepository.delete(passwordResetRecord.get());
            throw new AuthException(String.format("password reset token is expired: %s", email));
        }

        // reset the password
        user.get().setPasswordHash(passwordUtil.hashPassword(newPassword));
        user.get().setModifiedAt(LocalDateTime.now());
        userRepository.save(user.get());

        // delete the password reset token
        passwordResetTokenRepository.delete(passwordResetRecord.get());

        // create an audit log of resetting the password
        auditLogRepository.save(mapper.toAuditLog(user.get(), EventType.PASSWORD_RESET, String.format("user with email %s is reset the password", email)));

    }
}
