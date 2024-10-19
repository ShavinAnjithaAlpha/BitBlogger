package org.bitmonsters.apigateway.config;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.apigateway.security.AuthFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final AuthFilter authFilter;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("USER-SERVICE",
                        r -> r.path("/api/v1/users").filters(f -> f.filters(authFilter))
                                .uri("lb:http://USER-SERVICE")
                )
                .route("TAG-SERVICE",
                        r -> r.path("api/v1/tags").filters(f -> f.filters(authFilter))
                                .uri("lb:http://TAG-SERVICE"))
                .route("TOPIC-SERVICE",
                        r -> r.path("/api/v1/tags").filters(f -> f.filters(authFilter))
                                .uri("lb:http://TOPIC-SERVICE"))
                .route("POLL-SERVICE",
                        r -> r.path("api/v1/polls").filters(f -> f.filters(authFilter))
                                .uri("lb:http://POLL-SERVICE"))
                .route("LIKE-SERVICE",
                        r -> r.path("/api/v1/likes").filters(f -> f.filters(authFilter))
                                .uri("lb:http://LIKE-SERVICE"))
                .build();
    }

}
