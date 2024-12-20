package com.javatest.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class ApiKeyFilter implements WebFilter {

    private static final String API_KEY_HEADER = "X-API-KEY";

    @Value("${api.key}")
    private String API_KEY;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String apiKey = exchange.getRequest().getHeaders().getFirst(API_KEY_HEADER);

        if (API_KEY.equals(apiKey)) {
            return chain.filter(exchange);
        }

        exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}