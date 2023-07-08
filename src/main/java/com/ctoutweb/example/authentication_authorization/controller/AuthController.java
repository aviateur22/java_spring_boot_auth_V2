package com.ctoutweb.example.authentication_authorization.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctoutweb.example.authentication_authorization.model.LoginRequest;
import com.ctoutweb.example.authentication_authorization.model.LoginResponse;
import com.ctoutweb.example.authentication_authorization.model.RegisterRequest;
import com.ctoutweb.example.authentication_authorization.model.RegisterResponse;
import com.ctoutweb.example.authentication_authorization.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	private final AuthService authService;
	

	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}

	@PostMapping("/register")
	public RegisterResponse register(@RequestBody RegisterRequest request) {
		String id = authService.register(request);	
		return new RegisterResponse(id);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		LoginResponse response = authService.login(request);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
}
