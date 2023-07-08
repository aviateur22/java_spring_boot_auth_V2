package com.ctoutweb.example.authentication_authorization.validator.custom;


//import org.springframework.beans.BeanUtils;

import com.ctoutweb.example.authentication_authorization.model.RegisterRequest;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class InputMatchValidator implements ConstraintValidator<InputMatch, RegisterRequest> {
	
//	private String firstFieldName;
//    private String secondFieldName;
    private String message;

	@Override
	public void initialize(final InputMatch constraintAnnotation) {
//		firstFieldName = constraintAnnotation.first();
//        secondFieldName = constraintAnnotation.second();
        message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(RegisterRequest request, ConstraintValidatorContext context) {
		boolean valid = true;
		try
        {
			final String password = request.getPassword();
			final String cfmPssaword = request.getConfirmPassword();
			
//            final  Object firstObj = BeanUtils.getPropertyDescriptor(value.getClass(), firstFieldName);
//            final Object secondObj = BeanUtils.getPropertyDescriptor(value.getClass(), secondFieldName);
            
            valid =  password != null  && cfmPssaword.equals(password);
        }
        catch (final Exception ignore)
        {
            // ignore
        }

        if (!valid){
            context.buildConstraintViolationWithTemplate(message)
                    //.addPropertyNode(firstFieldName)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }
		
		
		return valid;
	}

}
