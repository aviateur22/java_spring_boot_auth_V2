package com.ctoutweb.example.authentication_authorization.service.storage;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ctoutweb.example.authentication_authorization.entity.UserFileEntity;
import com.ctoutweb.example.authentication_authorization.repository.FileRepositoryImp;
import com.ctoutweb.example.authentication_authorization.security.UserPrincipal;

@Service
public class UserFileService {
	
	private final StorageServiceImp storageService;
	private final FileRepositoryImp fileRepository;
	
	
	
	public UserFileService(StorageServiceImp storageService, FileRepositoryImp fileRepository) {
		super();
		this.storageService = storageService;
		this.fileRepository = fileRepository;
	}


	public String upload(MultipartFile file, String fileDescription, UserPrincipal user) {
		UserFileEntity uploadFilePath = storageService.saveFile(file, fileDescription, user);
		fileRepository.save(uploadFilePath);
		System.out.println(uploadFilePath);
		
		return null;
	}

	
	public Resource downloadFile(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}
}
