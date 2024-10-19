package org.bitmonsters.authserver.security;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.authserver.exception.UserNotFoundException;
import org.bitmonsters.authserver.model.EventType;
import org.bitmonsters.authserver.model.UserDetailsImpl;
import org.bitmonsters.authserver.repository.AuditLogRepository;
import org.bitmonsters.authserver.repository.UserRepository;
import org.bitmonsters.authserver.service.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuditLogRepository auditLogRepository;
    private final UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(username).orElseThrow(
                () -> new UserNotFoundException(String.format("user with email %s is not found", username))
        );

        return new UserDetailsImpl(user);
    }

    public void recordLastLogin(UserDetailsImpl userDetails) {
        userDetails.getUser().setLastLogin(LocalDateTime.now());
        userDetails.getUser().setFailureLoginAttempts(0);
        userRepository.save(userDetails.getUser());

        // lof the user login attempt in the audit log
        auditLogRepository.save(mapper.toAuditLog(userDetails.getUser(), EventType.LOGIN, "user logged in successfully"));
    }
}
