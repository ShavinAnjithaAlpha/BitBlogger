package org.bitmonsters.tagservice.config;

import org.bitmonsters.tagservice.security.AuthFilter;
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
                                .requestMatchers(HttpMethod.POST, "/api/v1/tags/posts/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/tags/posts/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/api/v1/tags").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/tags/*").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/tags/*").hasAuthority("ADMIN")
                                .requestMatchers("/api/v1/tags/history/**").hasAuthority("ADMIN")
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
