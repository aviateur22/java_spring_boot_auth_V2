package com.ctoutweb.example.authentication_authorization.validator.custom;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = InputMatchValidator.class)
@Documented
public @interface InputMatch {
	String message() default "The fields must match";
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
	
	String first();
    String second();
    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List
    {
    	InputMatch[] value();
    }

}
