package com.example.webapp;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableWebSecurity
@SecurityRequirement(name = "security_auth")
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@GetMapping("/user")
	public String user(@AuthenticationPrincipal Jwt jwt) {

		System.out.println(jwt.getTokenValue());
		return jwt.getSubject();
	}

	@GetMapping("/user1")
	public String user1(Authentication authentication) {

		var at = (JwtAuthenticationToken) authentication;

		System.out.println( "Principal: " + authentication.getAuthorities() );
		return "";
	}
}
