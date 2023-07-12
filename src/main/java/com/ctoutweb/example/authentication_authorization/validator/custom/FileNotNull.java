package com.ctoutweb.example.authentication_authorization.validator.custom;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileNotNullValidator.class)
@Documented
public @interface FileNotNull {
	String message() default "le fichier est obligatoire";
	Class<?>[] groups() default {};
	Class <? extends Payload>[] payload() default {};
}
