package com.ctoutweb.example.authentication_authorization.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctoutweb.example.authentication_authorization.model.User;
import com.ctoutweb.example.authentication_authorization.service.UserService;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
	
	private final UserService userService;
	
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}


	@GetMapping("/{id}")
	public ResponseEntity<User> getUserInformation(@PathVariable("id") Long id) {
		User user = userService.getUserInformation(id);
		return new ResponseEntity<>(user, HttpStatus.OK);		
	}
	
	@GetMapping("/find-by-email/{email}")
	public ResponseEntity<?>getUserByEmail(@PathVariable("email") String email){	
		User user = userService.getUserInformation(email);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
