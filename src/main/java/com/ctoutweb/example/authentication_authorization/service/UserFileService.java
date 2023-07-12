package com.ctoutweb.example.authentication_authorization.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ctoutweb.example.authentication_authorization.dto.FilePathDto;
import com.ctoutweb.example.authentication_authorization.entity.UserFileEntity;
import com.ctoutweb.example.authentication_authorization.exception.DeleteFileException;
import com.ctoutweb.example.authentication_authorization.exception.FindUserFileException;
import com.ctoutweb.example.authentication_authorization.exception.SchemaValidationException;
import com.ctoutweb.example.authentication_authorization.model.UploadFileRequest;
import com.ctoutweb.example.authentication_authorization.repository.FileRepositoryImp;
import com.ctoutweb.example.authentication_authorization.security.UserPrincipal;
import com.ctoutweb.example.authentication_authorization.service.storage.StorageServiceImp;
import com.ctoutweb.example.authentication_authorization.validator.ObjectValidator;

@Service
public class UserFileService {
	
	private final StorageServiceImp storageService;
	private final FileRepositoryImp fileRepository;	
	private final ObjectValidator<UploadFileRequest> uploadFileValidation;
	
	public UserFileService(StorageServiceImp storageService, FileRepositoryImp fileRepository, ObjectValidator<UploadFileRequest> uploadFileValidation) {
		super();
		this.storageService = storageService;
		this.fileRepository = fileRepository;
		this.uploadFileValidation = uploadFileValidation;
	}

	public  String upload(UploadFileRequest request, UserPrincipal user) {
		
		String validationError = uploadFileValidation.validate(request); 
		
		if(!validationError.isEmpty()){
			throw new SchemaValidationException(validationError);
		}	
		
		UserFileEntity userFile = storageService.saveFile(request, user);		
		long fileId = fileRepository.save(userFile);
		return String.valueOf(fileId);
	}
	
	public String deleteFileById(Long id) {
		UserFileEntity file = fileRepository.findFileById(id).orElseThrow(()->new DeleteFileException("impossible de trouver la réference du fichier en base de données"));
		storageService.deleteFile(file);
		int deleteFile = fileRepository.deleteById(id);
		
		if(deleteFile == 0) {
			throw new RuntimeException("existe pas en base de données");
		}
		return null;
	}
	
	public List<FilePathDto> findFileByUserId(Long id){	
		
	return fileRepository.findFileByUserId(id).orElseThrow()
				.stream()
				.map(userFile->new FilePathDto(
						userFile.getId(),
						userFile.getFileDescription(),
						userFile.getPath()))
				.collect(Collectors.toList());	
	}
	
	public FilePathDto download(Long id) {
		UserFileEntity userFile = fileRepository.findFileById(id).orElseThrow(()-> new FindUserFileException("fichier non présent en base de données"));
		
		FilePathDto file = new FilePathDto.FileBuilder()
				.id(userFile.getId())
				.description(userFile.getFileDescription())
				.path(userFile.getPath())
				.build();
		System.out.println(file);
		
		return file;
		
	}
}
