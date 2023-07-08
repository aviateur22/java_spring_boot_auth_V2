package com.ctoutweb.example.authentication_authorization.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ctoutweb.example.authentication_authorization.security.token.JwtDecoder;
import com.ctoutweb.example.authentication_authorization.security.token.JwtToUserPrincipalConverter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final JwtDecoder jwtDecoder;
	private final JwtToUserPrincipalConverter jwtToUserPrincipalConverter;

	
	

	public JwtAuthenticationFilter(JwtDecoder jwtDecoder, JwtToUserPrincipalConverter jwtToUserPrincipalConverter) {
		super();
		this.jwtDecoder = jwtDecoder;
		this.jwtToUserPrincipalConverter = jwtToUserPrincipalConverter;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		extractTokenFromRequest(request)
			.map(token->jwtDecoder.decode(token))
			.map(jwtDecode->jwtToUserPrincipalConverter.convert(jwtDecode))
			.map(userPrincipal->new UserPrincipalAuthenticationToken(userPrincipal))
			.ifPresent(authentication->SecurityContextHolder.getContext().setAuthentication(authentication));
		
		filterChain.doFilter(request, response);
		
	}
	
	private Optional<String> extractTokenFromRequest(HttpServletRequest request){
		var token = request.getHeader("authorization");
		
		if(!StringUtils.hasText(token) || !token.startsWith("Bearer ")) return Optional.empty();
		
		return Optional.of(token.substring(7));		
	}
	

}
