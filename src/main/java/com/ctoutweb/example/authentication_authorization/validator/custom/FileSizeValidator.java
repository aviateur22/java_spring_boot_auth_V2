package com.ctoutweb.example.authentication_authorization.validator.custom;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {	
	private String message;
	private long size;
	

	@Override
	public void initialize(FileSize fileSizeAnnotation) {
		message= fileSizeAnnotation.message();
		size = fileSizeAnnotation.size();
	}
	
	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {		
		if(file.getSize() <= size) {			
			return true;
		}
		
		context.buildConstraintViolationWithTemplate(message)     
        .addConstraintViolation()
        .disableDefaultConstraintViolation();
		
		return false;
	}

}
