package org.bitmonsters.authserver.service;

import lombok.SneakyThrows;
import org.bitmonsters.authserver.exception.AccountDisabledException;
import org.bitmonsters.authserver.model.UserDetailsImpl;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountLockedException;
import java.time.LocalDateTime;

@Service
public class UserAccountStatusService {

    private final Integer ACCOUNT_LOCKED_HOURS = 1;

    @SneakyThrows
    public void check(UserDetailsImpl userDetails) {
        // check account active status
        checkAccountActiveStatus(userDetails);

        // check whether if the account is expired
        checkAccountExpiredStatus(userDetails);

        // check if the account is locked
        checkLockedStatus(userDetails);

    }

    private void checkAccountExpiredStatus(UserDetailsImpl userDetails) {
        if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException(String.format("failed to authenticate because account associated with email %s is expired", userDetails.getUsername()));
        }
    }

    private void checkAccountActiveStatus(UserDetailsImpl userDetails) {
        if (!userDetails.isEnabled()) {
            throw new AccountDisabledException(userDetails.getUsername());
        }
    }

    @SneakyThrows
    private void checkLockedStatus(UserDetailsImpl userDetails) {
        if (!userDetails.isAccountNonLocked()) {
            // get the time when the account is locked
            LocalDateTime lockedAt = userDetails.getUser().getLockedAt();
            if (lockedAt.plusHours(ACCOUNT_LOCKED_HOURS).isBefore(LocalDateTime.now())) {
                // release the account
                userDetails.getUser().setLocked(false);
                userDetails.getUser().setLockedAt(null);
                userDetails.getUser().setFailureLoginAttempts(0);
            } else {
                // account is further locked
                throw new AccountLockedException(String.format("failed to authenticate because account is locked until %s", lockedAt.plusHours(ACCOUNT_LOCKED_HOURS).toString()));
            }
        }

    }

}
