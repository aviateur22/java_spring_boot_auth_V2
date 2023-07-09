package com.ctoutweb.example.authentication_authorization.service.storage;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ctoutweb.example.authentication_authorization.entity.UserFileEntity;
import com.ctoutweb.example.authentication_authorization.security.UserPrincipal;

@Service
public class StorageServiceImp implements StorageService {
	
	private final Path root = Paths.get("uploads");

	@Override
	public UserFileEntity saveFile(MultipartFile file, String fileDescription, UserPrincipal user) {
		try {
			String UUIDName = this.generateUUIDName(file.getOriginalFilename());
			Path filePath = this.root.resolve(UUIDName + getFileExtension(file.getContentType()));	
			Files.copy(file.getInputStream(), filePath);
			return this.getUserFileEntity(file, String.valueOf(filePath), UUIDName, fileDescription, user.getId());
			
		} catch (Exception e) {
			
			if (e instanceof FileAlreadyExistsException) {
		        throw new RuntimeException("A file of that name already exists.");
		      }

		      throw new RuntimeException(e.getMessage());
		}
		
	}

	@Override
	public Resource downloadFile(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String getFileExtension(String mimeType) {
		return ".pdf";
	}
	
	private String generateUUIDName(String originalName) {
		final long currentTimeMillis = System.currentTimeMillis();		
		return String.valueOf(UUID.nameUUIDFromBytes((originalName +" " + currentTimeMillis).getBytes()));
	}
	
	private UserFileEntity getUserFileEntity(MultipartFile file, String filePath, String filename, String fileDescription, Long userId) {
		Instant createdAt = Instant.now();
		return new UserFileEntity.UserFileBuilder()
				.fileName(filename)
				.path(filePath)
				.fileDescription(fileDescription)
				.fileType(file.getContentType())
				.Userid(userId)
				.createdAt(Timestamp.from(createdAt))
				.updatedAt(Timestamp.from(createdAt))
				.build();
	}
	
	

}
