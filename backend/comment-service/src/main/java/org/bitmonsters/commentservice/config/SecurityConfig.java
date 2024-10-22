package org.bitmonsters.commentservice.config;

import org.bitmonsters.commentservice.security.AuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(HttpMethod.POST, "/api/v1/posts/*/comments").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/api/v1/comments/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/comments/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/api/v1/comments/*/replies").authenticated()
                                .requestMatchers(HttpMethod.POST, "/api/v1/comments/*/reactions").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/api/v1/comments/*/reactions").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/comments/*/reactions").authenticated()
                                .requestMatchers(HttpMethod.GET, "/api/v1/comments/*/reactions/me").authenticated()
                                .requestMatchers(HttpMethod.POST, "/api/v1/comments/*/reports").authenticated()
                                .requestMatchers("/api/v1/comments/reports").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v1/comments/*/reports").hasAuthority("ADMIN")
                                .anyRequest().permitAll()
                )
                .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();

    }

    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }

}
