package com.ctoutweb.example.authentication_authorization.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ctoutweb.example.authentication_authorization.entity.UserFileEntity;
import com.ctoutweb.example.authentication_authorization.exception.DeleteFileException;
import com.ctoutweb.example.authentication_authorization.exception.FindUserFileException;
import com.ctoutweb.example.authentication_authorization.model.FileResponse;
import com.ctoutweb.example.authentication_authorization.repository.FileRepositoryImp;
import com.ctoutweb.example.authentication_authorization.security.UserPrincipal;
import com.ctoutweb.example.authentication_authorization.service.storage.StorageServiceImp;

@Service
public class UserFileService {
	
	private final StorageServiceImp storageService;
	private final FileRepositoryImp fileRepository;	
	
	public UserFileService(StorageServiceImp storageService, FileRepositoryImp fileRepository) {
		super();
		this.storageService = storageService;
		this.fileRepository = fileRepository;
	}

	public  String upload(MultipartFile file, String fileDescription, UserPrincipal user) {
		UserFileEntity userFile = storageService.saveFile(file, fileDescription, user);		
		fileRepository.save(userFile);
		return null;
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
	
	public List<FileResponse> findFileByUserId(Long id){	
		
	return fileRepository.findFileByUserId(id).orElseThrow()
				.stream()
				.map(userFile->new FileResponse(
						userFile.getId(),
						userFile.getFileDescription(),
						userFile.getFileName(),
						storageService.getFileUri(userFile),
						userFile.getFileType()))
				.collect(Collectors.toList());	
	}
	
	public FileResponse download(Long id) {
		UserFileEntity userFile = fileRepository.findFileById(id).orElseThrow(()-> new FindUserFileException("fichier non présent en base de données"));
		String fileUri = storageService.getFileUri(userFile);	
		
		FileResponse file = new FileResponse.FileBuilder()
				.id(userFile.getId())
				.description(userFile.getFileDescription())
				.fileName(userFile.getFileName())
				.fileType(userFile.getFileType())
				.fileUri(fileUri)
				.build();
		System.out.println(file);
		
		return file;
		
	}
}
