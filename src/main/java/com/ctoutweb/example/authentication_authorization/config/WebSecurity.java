package com.ctoutweb.example.authentication_authorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ctoutweb.example.authentication_authorization.security.JwtAuthenticationFilter;
import com.ctoutweb.example.authentication_authorization.security.UserPrincipalDetailService;

@Configuration
@EnableWebSecurity
public class WebSecurity {
	
	private final UserPrincipalDetailService userPrincipalDetailService;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
	public WebSecurity(UserPrincipalDetailService userPrincipalDetailService, JwtAuthenticationFilter jwtAuthenticationFilter) {
		super();
		this.userPrincipalDetailService = userPrincipalDetailService;
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		http
			.csrf(x->x.disable())
			.sessionManagement(x->x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.formLogin(x->x.disable())
			.authorizeHttpRequests(x->x
					.requestMatchers("/api/v1/auth/**").permitAll()
					.requestMatchers("/api/v1/home").permitAll()
					.requestMatchers("/api/v1/user/**").permitAll()
					.requestMatchers("/api/v1/file/**").permitAll()
					.anyRequest().authenticated());
		
		return http.build();		
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
	}
	
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		
		return http.getSharedObject(AuthenticationManagerBuilder.class)		
				.userDetailsService(userPrincipalDetailService)
				.passwordEncoder(passwordEncoder())
				.and()
				.build();
	}


}
