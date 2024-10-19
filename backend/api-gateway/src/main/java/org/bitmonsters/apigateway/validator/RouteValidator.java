package org.bitmonsters.apigateway.validator;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    private final static List<String> unprotectedUrls = List.of("/login");

    public Predicate<ServerHttpRequest> isSecured = serverHttpRequest -> unprotectedUrls.stream().noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));

}
