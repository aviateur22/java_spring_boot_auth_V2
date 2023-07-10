package com.ctoutweb.example.authentication_authorization.service.storage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ctoutweb.example.authentication_authorization.entity.UserFileEntity;
import com.ctoutweb.example.authentication_authorization.exception.DeleteFileException;
import com.ctoutweb.example.authentication_authorization.security.UserPrincipal;

@Service
public class StorageServiceImp implements StorageService {
	
	private final Path root = Paths.get("uploads");	

	public StorageServiceImp() {
		super();		
	}

	@Override
	public UserFileEntity saveFile(MultipartFile file, String fileDescription, UserPrincipal user) {
		try {
			
			this.isFileExtensionValid(file);
			
			String UUIDName = this.generateUUIDName(file.getOriginalFilename());
			String fileName = UUIDName + "." + getFileExtension(file.getContentType());
			
			Path filePath = this.root.resolve(fileName);			
			Files.copy(file.getInputStream(), filePath);
			
			UserFileEntity fileData = this.getUserFileEntity(file, String.valueOf(filePath), fileName, fileDescription, user.getId());			
			return fileData;
			
		} catch (Exception e) {
			
			if (e instanceof FileAlreadyExistsException) {
		        throw new RuntimeException("A file of that name already exists.");
		      }

		      throw new RuntimeException(e.getMessage());
		}
		
	}

	@Override
	public String getFileUri(UserFileEntity file) {
		try {
			Path path = root.resolve(file.getFileName());
			Resource resource = new UrlResource(path.toUri());
			
			if(!resource.exists() || !resource.isReadable()) {
				return "unkow_file_url";
			}
			
			return resource.getURI().toString();
				
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "unkow_file_url";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "unkow_file_url";
		}
		
		
	}
	
	public void deleteFile(UserFileEntity file) {
		try {
			Path filePath = this.root.resolve(file.getFileName());
			
			Files.delete(filePath);
		}
		catch (Exception e) {					
			if (!(e instanceof NoSuchFileException)) {
				System.out.println(e.getClass());
				throw new DeleteFileException("Erreur suppression du fichier");
			}
			System.out.println(e.getClass());
		}
	}
	
	private String getFileExtension(String mimeType) {
		switch(mimeType.toLowerCase()) {
			case "application/pdf": {
				return "pdf";			
			}		
			case "image/png": {
				return "png";				
			}			
			case "image/jpeg": {
				return "jpg";				
			}	
			default: throw new RuntimeException("file is not accepted");
		}
	}
	
	@SuppressWarnings("null")
	private boolean isFileExtensionValid(MultipartFile file) throws IOException {
		InputStream fileStream = file.getInputStream();		
		byte[] magicBytes = new byte[3];		
		fileStream.read(magicBytes);
		fileStream.close();		
		
		switch(file.getContentType().toLowerCase()) {
			case "application/pdf": {
				if(!FileType.PDF.is(magicBytes)) throw new RuntimeException("file is not a valid PDF");
				
			}			
			break;
			
			case "image/png": {
				if(!FileType.PNG.is(magicBytes)) throw new RuntimeException("file is not a valid PNG");				
			}
			break;
			
			case "image/jpeg": {
				if(!FileType.JPEG.is(magicBytes)) throw new RuntimeException("file is not a valid JPEG");				
			}
			break;			
			default: throw new RuntimeException("file is not accepted");
		}
		return true;
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
				.fileType(this.getFileExtension(file.getContentType()))
				.Userid(userId)
				.createdAt(Timestamp.from(createdAt))
				.updatedAt(Timestamp.from(createdAt))
				.build();
	}

}
