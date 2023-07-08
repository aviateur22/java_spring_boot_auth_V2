package com.ctoutweb.example.authentication_authorization.security.token;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ctoutweb.example.authentication_authorization.security.UserPrincipal;

@Component
public class JwtIssuer {
	private final JwtProperties jwtProperties;

	public JwtIssuer(JwtProperties jwtProperties) {
		super();
		this.jwtProperties = jwtProperties;
	}
	
	public String issue(UserPrincipal user) {
		Instant expiredAt = Instant.now().plus(Duration.ofHours(5));
		List<String> authorities = user.getAuthorities().stream().map(auth->auth.getAuthority().toString()).collect(Collectors.toList());
		
		return JWT.create()
				.withSubject(user.getEmail())
				.withIssuer(jwtProperties.getIssuer())
				.withJWTId(UUID.randomUUID().toString())
				.withExpiresAt(expiredAt)
				.withClaim("authorities", authorities)
				.withClaim("id", user.getId())
				.sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
	}

}
