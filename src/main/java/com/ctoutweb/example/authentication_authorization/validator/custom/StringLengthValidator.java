package com.ctoutweb.example.authentication_authorization.validator.custom;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StringLengthValidator implements ConstraintValidator<StringLength, String>{
	
	private String message;
	private int length;

	@Override
	public void initialize(StringLength stringLengthAnnotation) {
		message = stringLengthAnnotation.message();
		length = stringLengthAnnotation.length();
	}	

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(value.length() >= length) {
			return true;
		}
		
		context.buildConstraintViolationWithTemplate(message)     
        .addConstraintViolation()
        .disableDefaultConstraintViolation();
		
		return false;
	}

}
