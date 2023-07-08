package com.ctoutweb.example.authentication_authorization.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ctoutweb.example.authentication_authorization.exception.SchemaValidationException;
import com.ctoutweb.example.authentication_authorization.model.LoginRequest;
import com.ctoutweb.example.authentication_authorization.model.LoginResponse;
import com.ctoutweb.example.authentication_authorization.model.RegisterRequest;
import com.ctoutweb.example.authentication_authorization.repository.UserRepositoryImp;
import com.ctoutweb.example.authentication_authorization.security.UserPrincipal;
import com.ctoutweb.example.authentication_authorization.security.token.JwtIssuer;
import com.ctoutweb.example.authentication_authorization.validator.ObjectValidator;

@Service
public class AuthService {
	private final ObjectValidator<RegisterRequest> registerSchemaValidaton;
	private final ObjectValidator<LoginRequest> loginSchemaValidaton;
	private final UserRepositoryImp userRepositoryImp;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtIssuer jwtIssuer;
	
	public AuthService(UserRepositoryImp userRepositoryImp, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtIssuer jwtIssuer, ObjectValidator<RegisterRequest> registerSchemaValidaton, ObjectValidator<LoginRequest> loginSchemaValidaton) {
		super();
		this.registerSchemaValidaton = registerSchemaValidaton;
		this.loginSchemaValidaton = loginSchemaValidaton;
		this.userRepositoryImp = userRepositoryImp;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtIssuer = jwtIssuer;
	}

	public String register(RegisterRequest request) {
		
		String validationError = registerSchemaValidaton.validate(request); 
			
		if(!validationError.isEmpty()){
			throw new SchemaValidationException(validationError);
		}	
		
		
		request = new RegisterRequest(request.getEmail(),passwordEncoder.encode(request.getPassword()), request.getCityId());
		
		long id = userRepositoryImp.save(request);
		return String.valueOf(id);
	}
	
	public LoginResponse login(LoginRequest request) {
		
		String validationError = loginSchemaValidaton.validate(request);
		
		if(!validationError.isEmpty()){
			throw new SchemaValidationException(validationError);
		}	
		
		var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		var token = jwtIssuer.issue(userPrincipal);
		
		return new LoginResponse(token);
	}
}
