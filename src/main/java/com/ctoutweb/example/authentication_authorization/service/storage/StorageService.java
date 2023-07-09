package com.ctoutweb.example.authentication_authorization.service.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.ctoutweb.example.authentication_authorization.entity.UserFileEntity;
import com.ctoutweb.example.authentication_authorization.security.UserPrincipal;

public interface StorageService {

	UserFileEntity saveFile(MultipartFile file, String fileDescription, UserPrincipal user);
	Resource downloadFile(String fileName);
	
}