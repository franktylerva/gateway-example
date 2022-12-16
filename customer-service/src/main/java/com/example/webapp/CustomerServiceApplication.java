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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@SpringBootApplication
@RestController
@EnableWebSecurity
@OpenAPIDefinition(
		info = @Info(title = "Customer Service", description = "Customer Service", version = "v1"),
		servers = { @Server(url = "http://127.0.0.1:8080/customer", description = "Gateway" ) }
)
@Validated
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@GetMapping("/user")
	public UserResponse user(@AuthenticationPrincipal Jwt jwt) {
		return new UserResponse("John Doe", jwt.getSubject() );
	}

	@PostMapping("/user")
	public void createUser(@RequestBody @Valid UserRequest user, Authentication authentication) {
		System.out.println("User: " + user.name() );
	}
}
