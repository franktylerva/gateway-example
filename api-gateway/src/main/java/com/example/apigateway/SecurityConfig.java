package com.example.apigateway;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.web.server.OAuth2AuthorizationRequestRedirectWebFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchanges -> exchanges
                        .anyExchange().authenticated()
                )
                .oauth2Login();
        return http.build();
    }

//    @Bean
//    public ReactiveAuthorizationManager<ServerWebExchange> reactiveAuthorizationManager() {
//        return new ReactiveAuthorizationManager<ServerWebExchange>() {
//            @Override
//            public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, ServerWebExchange serverWebExchange) {
//
//                if(serverWebExchange.getRequest().getHeaders().containsKey("F5-USER")) {
//                    return Mono.just( new AuthorizationDecision(true) );
//                }
//                return Mono.just( new AuthorizationDecision(false) );
//            };
//        };
//    }

}
