package com.ctoutweb.example.authentication_authorization.exception;

public class FileExtensionException extends RuntimeException {
	public FileExtensionException() {
		super("file extension not valid");
	}
	
	public FileExtensionException(String message) {
		super(message);
	}
}
