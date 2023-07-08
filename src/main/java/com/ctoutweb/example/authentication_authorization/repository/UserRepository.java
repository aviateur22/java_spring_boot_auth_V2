package com.ctoutweb.example.authentication_authorization.repository;

import java.util.Optional;

import com.ctoutweb.example.authentication_authorization.model.RegisterRequest;
import com.ctoutweb.example.authentication_authorization.model.User;

public interface UserRepository {

	public Long save(RegisterRequest request);
	public Optional<User> findById(long id);
	public Optional<User> findByMail(String email);
}
