package org.bitmonsters.authserver.security;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.authserver.model.EventType;
import org.bitmonsters.authserver.model.UserDetailsImpl;
import org.bitmonsters.authserver.repository.AuditLogRepository;
import org.bitmonsters.authserver.repository.UserRepository;
import org.bitmonsters.authserver.service.UserAccountStatusService;
import org.bitmonsters.authserver.service.UserMapper;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final Integer MAXIMUM_FAILURE_LOGIN_ATTEMPTS = 5;

    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    private final UserDetailsService userDetailsService;
    private final UserAccountStatusService userAccountStatusService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuditLogRepository auditLogRepository;
    private final UserMapper mapper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, authentication,
                () -> this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.onlySupports",
                        "Only UsernamePasswordAuthenticationToken is supported"));

        String email = authentication.getName();
        // load the user from the user repository
        UserDetailsImpl user = (UserDetailsImpl) userDetailsService.loadUserByUsername(email);

        if (user != null) {
            // check the user account status before grating access tokens
            userAccountStatusService.check(user);
            userRepository.save(user.getUser());

            // match the password with the hashed password in the user object
            if (passwordEncoder.matches((String) authentication.getCredentials(), user.getPassword())) {
                // password is matched
                // record the last login time in the database
                ((UserDetailsServiceImpl) userDetailsService).recordLastLogin((UserDetailsImpl) user);

                // returned a successful user authentication token
                return new UsernamePasswordAuthenticationToken(
                        email,
                        null,
                        user.getAuthorities()
                );
            } else {
                // record the failed attempt in the audit log
                auditLogRepository.save(
                        mapper.toAuditLog(user.getUser(), EventType.LOGIN_FAILURE, "user login failed: bad credentials"));
                // refresh the failure login attempts
                user.getUser().setFailureLoginAttempts(
                        user.getUser().getFailureLoginAttempts() == null ? 1 : user.getUser().getFailureLoginAttempts() + 1);
                if (user.getUser().getFailureLoginAttempts() >= MAXIMUM_FAILURE_LOGIN_ATTEMPTS) {
                    user.getUser().setLocked(true);
                    user.getUser().setLockedAt(LocalDateTime.now());
                }
                userRepository.save(user.getUser());

                throw new BadCredentialsException("bad credentials");
            }
        } else {
            throw new BadCredentialsException("bad credentials");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
