package com.ctoutweb.example.authentication_authorization.service.storage;

import com.ctoutweb.example.authentication_authorization.entity.UserFileEntity;
import com.ctoutweb.example.authentication_authorization.model.UploadFileRequest;
import com.ctoutweb.example.authentication_authorization.security.UserPrincipal;

public interface StorageService {

	UserFileEntity saveFile(UploadFileRequest request, UserPrincipal user);
	
	
}
