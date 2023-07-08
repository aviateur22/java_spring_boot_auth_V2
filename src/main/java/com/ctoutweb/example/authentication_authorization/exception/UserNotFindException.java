package com.ctoutweb.example.authentication_authorization.exception;

public class UserNotFindException extends RuntimeException {

	public UserNotFindException(String Message) {
		super(Message);
	}
}
