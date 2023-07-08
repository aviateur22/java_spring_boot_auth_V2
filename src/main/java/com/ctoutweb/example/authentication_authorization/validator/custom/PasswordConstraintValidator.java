package com.ctoutweb.example.authentication_authorization.validator.custom;


import java.util.Arrays;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<PasswordConstraint, String> {
	private String message;
	
	@Override
	public void initialize(PasswordConstraint constraintAnnotation) {
	message= constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		
		PasswordValidator passwordValidator = new PasswordValidator(Arrays.asList(
				new LengthRule(12, 30),
				new CharacterRule(EnglishCharacterData.UpperCase, 1),
				new CharacterRule(EnglishCharacterData.LowerCase, 1),
				new CharacterRule(EnglishCharacterData.Digit, 1),
				new CharacterRule(EnglishCharacterData.Special, 1),
				new WhitespaceRule()				
			));
		
		RuleResult result =  passwordValidator.validate(new PasswordData(password));
		
		if(result.isValid()) {
			return true;
		}
		
		context.buildConstraintViolationWithTemplate(message)     
        .addConstraintViolation()
        .disableDefaultConstraintViolation();
		
		return false;
	}

}
