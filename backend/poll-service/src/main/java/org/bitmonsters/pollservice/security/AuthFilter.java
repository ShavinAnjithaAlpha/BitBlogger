package org.bitmonsters.pollservice.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        // extract the user id from the request header
        String userId = request.getHeader("userId");
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (userId != null) {
            // extract the user role using custom role header in the http request
            String rolesHeader = request.getHeader("roles");
            if (rolesHeader != null) {
                // otherwise convert the role string into set of granted authorities
                authorities = Arrays.stream(rolesHeader.split(";")).map(role -> new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return role;
                    }
                }).collect(Collectors.toList());
            }

            // create an authentication tag using the user id and roles provided in the request header
            var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    Long.valueOf(userId),
                    null,
                    authorities
            );
            // create an authentication object with appropriate details
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        // continue the filter chain
        filterChain.doFilter(request, response);
    }
}
