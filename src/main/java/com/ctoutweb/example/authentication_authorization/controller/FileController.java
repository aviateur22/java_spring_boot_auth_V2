package com.ctoutweb.example.authentication_authorization.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ctoutweb.example.authentication_authorization.model.FileResponse;
import com.ctoutweb.example.authentication_authorization.model.UploadResponse;
import com.ctoutweb.example.authentication_authorization.security.UserPrincipal;
import com.ctoutweb.example.authentication_authorization.service.UserFileService;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {
	
	private UserFileService userFileService;
	
	public FileController(UserFileService userFileService) {
		super();
		this.userFileService = userFileService;
	}


	
	
	@PostMapping("/upload")
	public ResponseEntity<UploadResponse> Upload(@AuthenticationPrincipal UserPrincipal user, @RequestPart MultipartFile file, @RequestPart String fileDescription){
		String uploadFilePath = userFileService.upload(file, fileDescription, user);		
		return new ResponseEntity<>(new UploadResponse(uploadFilePath), HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> DeleteFileById(@PathVariable("id") Long id) throws IOException{
		userFileService.deleteFileById(id);
		return new ResponseEntity<>("Fichier supprimé", HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<List<FileResponse>> FindFileByUserId(@PathVariable("id") Long id){
		List<FileResponse> userFiles = userFileService.findFileByUserId(id);
		return new ResponseEntity<>(userFiles, HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FileResponse> findFileById(@PathVariable("id") Long id){
		FileResponse file = userFileService.download(id);
		return new ResponseEntity<>(file, HttpStatus.OK);
	}
	
	
}
