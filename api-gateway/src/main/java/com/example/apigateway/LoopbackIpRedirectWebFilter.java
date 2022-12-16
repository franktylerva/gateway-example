package com.example.apigateway;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class LoopbackIpRedirectWebFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        String host = exchange.getRequest().getURI().getHost();
        if (host != null && host.equals("localhost")) {
            UriComponents uri = UriComponentsBuilder.fromHttpRequest(exchange.getRequest()).host("127.0.0.1").build();
            exchange.getResponse().setStatusCode(HttpStatus.PERMANENT_REDIRECT);
            exchange.getResponse().getHeaders().setLocation(uri.toUri());
            return Mono.empty();
        }
        return chain.filter(exchange);
    }

}