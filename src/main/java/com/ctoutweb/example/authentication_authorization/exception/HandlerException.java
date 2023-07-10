package com.ctoutweb.example.authentication_authorization.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.ctoutweb.example.authentication_authorization.model.ErrorResponse;

@ControllerAdvice
public class HandlerException {
	
	@ExceptionHandler(value = {RuntimeException.class})
	public ResponseEntity<ErrorResponse>RunTimeException() {
		ErrorResponse response = new ErrorResponse("Erreur interne");
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value =  {UserNotFindException.class})	
	public ResponseEntity<ErrorResponse>UserNotFindException(UserNotFindException ex, WebRequest request){		
		ErrorResponse response = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value= {SchemaValidationException.class})
	public ResponseEntity<ErrorResponse>SchemaValidationException(SchemaValidationException ex, WebRequest request){
		ErrorResponse response = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value= {DeleteFileException.class})
	public ResponseEntity<ErrorResponse>DeleteFileException(DeleteFileException ex, WebRequest request){
		ErrorResponse response = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value= {FindUserFileException.class})
	public ResponseEntity<ErrorResponse>FindUserFileException(FindUserFileException ex, WebRequest request){
		ErrorResponse response = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
