package com.example.webapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
@EnableWebSecurity
@OpenAPIDefinition(
		info = @Info(title = "Customer Service", description = "Customer Service", version = "v1"),
		servers = { @Server(url = "http://127.0.0.1:8080/customer", description = "Gateway" ) }
)
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@GetMapping("/user")
	public String user(@AuthenticationPrincipal Jwt jwt) {

		System.out.println(jwt.getTokenValue());
		return jwt.getSubject();
	}

	@GetMapping("/user1")
	public Map<String,Object> user1(Authentication authentication) {

		var at = (JwtAuthenticationToken) authentication;

		return Map.of(
				"getCredentials", authentication.getCredentials(),
				"getAuthorities", authentication.getAuthorities(),
				"getDetails", authentication.getDetails(),
				"getTokenAttributes", ((JwtAuthenticationToken) authentication).getTokenAttributes()
		);
	}
}
