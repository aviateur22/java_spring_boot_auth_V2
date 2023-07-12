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
import com.ctoutweb.example.authentication_authorization.exception.FileExtensionException;
import com.ctoutweb.example.authentication_authorization.model.UploadFileRequest;
import com.ctoutweb.example.authentication_authorization.security.UserPrincipal;

@Service
public class StorageServiceImp implements StorageService {
	
	private final Path root = Paths.get("uploads");	

	public StorageServiceImp() {
		super();		
	}

	@Override
	public UserFileEntity saveFile(UploadFileRequest request, UserPrincipal user) {
		try {
			MultipartFile file = request.getFile();
			
			String fileDescription = request.getDescription();
			
			String fileExtension = this.getFileExtension(file);			
			
			String UUIDName = this.generateUUIDName(file.getOriginalFilename());
			String fileName = UUIDName + "." + fileExtension;
			
			Path filePath = this.root.resolve(fileName);			
			Files.copy(file.getInputStream(), filePath);
			
			return this.getUserFileEntity(file, this.getFilePath(fileName), fileName, fileDescription, fileExtension, user.getId());			
			
		} catch (Exception e) {
			
			if (e instanceof FileAlreadyExistsException) {
		        throw new RuntimeException("A file of that name already exists.");
		    }
			
			
			if(e instanceof FileExtensionException) {
				throw new FileExtensionException(request.getFile().getOriginalFilename() + " n'est pas au format PDF, JPG ou PNG");
			}

		      throw new RuntimeException(e.getMessage());
		}
		
	}
	
	private String getFilePath(String fileName) {
		try {
			Path path = root.resolve(fileName);
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
	
	@SuppressWarnings("null")
	private String getFileExtension(MultipartFile file) throws IOException {
		InputStream fileStream = file.getInputStream();		
		byte[] magicBytes = new byte[3];		
		fileStream.read(magicBytes);
		fileStream.close();		
		
		switch(file.getContentType().toLowerCase()) {
			case "application/pdf": {
				if(!FileType.PDF.is(magicBytes)) throw new RuntimeException("file is not a valid PDF");
				return "pdf";
				
			}			
			
			case "image/png": {
				if(!FileType.PNG.is(magicBytes)) throw new RuntimeException("file is not a valid PNG");
				return "png";
			}			
			
			case "image/jpeg": {
				if(!FileType.JPEG.is(magicBytes)) throw new RuntimeException("file is not a valid JPEG");
				return "jpg";
			}
						
			default: throw new FileExtensionException();
		}		
	}
	
	private String generateUUIDName(String originalName) {
		final long currentTimeMillis = System.currentTimeMillis();		
		return String.valueOf(UUID.nameUUIDFromBytes((originalName +" " + currentTimeMillis).getBytes()));
	}
	
	private UserFileEntity getUserFileEntity(MultipartFile file, String filePath, String filename, String fileDescription, String fileExtension, Long userId) {
		Instant createdAt = Instant.now();
		return new UserFileEntity.UserFileBuilder()
				.fileName(filename)
				.path(filePath)
				.fileDescription(fileDescription)
				.fileType(fileExtension)
				.Userid(userId)
				.createdAt(Timestamp.from(createdAt))
				.updatedAt(Timestamp.from(createdAt))
				.build();
	}

}
