package com.ctoutweb.example.authentication_authorization.validator;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@Component
public class ObjectValidator <T> {
	
	private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	private final Validator validator = validatorFactory.getValidator();
	
	public ObjectValidator() {
		// TODO Auto-generated constructor stub
	}
	
	public String validate(T request) {
		Set<ConstraintViolation<T>> validationErros = validator.validate(request);
		
		if(!validationErros.isEmpty()) {
			return validationErros
			.stream()
			.map(ConstraintViolation::getMessage)
			.collect(Collectors.toSet()).iterator().next();		
			
		}
		
		return "";
	}

}
