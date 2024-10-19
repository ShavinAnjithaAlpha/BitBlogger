package org.bitmonsters.apigateway.security;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.bitmonsters.apigateway.util.JwtUtil;
import org.bitmonsters.apigateway.validator.RouteValidator;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
@RefreshScope
@RequiredArgsConstructor
public class AuthFilter implements GatewayFilter {

    private final RouteValidator routeValidator;

    private final JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String token = null;
        ServerHttpRequest request = exchange.getRequest();

        if (routeValidator.isSecured.test(request)) {
            System.out.println("Validating authentication token");
            if (this.isCredMissing(request)) {
                System.out.println("in error");
                return this.onError(exchange, "Credentials Missing", HttpStatus.UNAUTHORIZED);
            }

            token = Objects.requireNonNull(request.getHeaders().get("Authorization")).toString().split(" ")[1];

            if (!jwtUtil.validateToken(token)) {
                return this.onError(exchange, "Auth header invalid", HttpStatus.UNAUTHORIZED);
            } else {
                System.out.println("Authentication is successful");
            }
            this.populateRequestWithHeader(exchange, token);

        }
        return chain.filter(exchange);
    }

    private void populateRequestWithHeader(ServerWebExchange exchange, String token) {
        Claims claims = jwtUtil.extractAllClaims(token);

        exchange.getRequest()
                .mutate()
                .header("userId", claims.getSubject())
//                .header("role", String.valueOf(claims.get("role")))
                .build();
    }

    private boolean isCredMissing(ServerHttpRequest request) {
        return !(request.getHeaders().containsKey("Authorization"));
    }

    private Mono<Void> onError(ServerWebExchange exchange, String error, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();

    }
}
