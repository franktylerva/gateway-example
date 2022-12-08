package com.example.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.MatcherSecurityWebFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.WebFilterChainProxy;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@SpringBootApplication
@EnableWebFluxSecurity
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	ApplicationListener<ApplicationReadyEvent> ready( SecurityWebFilterChain... filters ) {
		return new ApplicationListener<ApplicationReadyEvent>() {
			@Override
			public void onApplicationEvent(ApplicationReadyEvent event) {
				var filterList = Arrays.asList( filters );
				filterList.stream().forEach(f -> {

					if(f instanceof MatcherSecurityWebFilterChain) {
						var matcher = (MatcherSecurityWebFilterChain) f;
						matcher.getWebFilters().subscribe(webFilter -> {
							System.out.println(webFilter.getClass().getName() );
						});
					}
				});

			}
		};
	}

}
