package com.example.apigateway;

import io.netty.handler.ssl.ClientAuth;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.x509.SubjectDnX509PrincipalExtractor;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;

import java.io.File;
import java.security.PrivateKey;

import static org.springframework.security.config.Customizer.withDefaults;

@SpringBootApplication
@EnableWebFluxSecurity
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder, TokenRelayGatewayFilterFactory filterFactory) {
		return builder.routes()
				.route(p -> p
						.path("/user")
						.filters(f -> f.filter(filterFactory.apply()))
						.uri("http://127.0.0.1:8082"))
				.build();
	}

//	@Bean
//	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//
//		SubjectDnX509PrincipalExtractor principalExtractor =
//				new SubjectDnX509PrincipalExtractor();
//
//		principalExtractor.setSubjectDnRegex("OU=(.*?)(?:,|$)");
//
//		ReactiveAuthenticationManager authenticationManager = authentication -> {
//			authentication.setAuthenticated(true);
//			return Mono.just(authentication);
//		};
//
//		http
//				.x509(x509 -> x509
//						.principalExtractor(principalExtractor)
//						.authenticationManager(authenticationManager)
//				)
//				.authorizeExchange(exchanges -> exchanges
//						.anyExchange().authenticated()
//				);
//		return http.build();
//	}
}
