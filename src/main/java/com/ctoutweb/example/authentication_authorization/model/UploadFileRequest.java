package com.ctoutweb.example.authentication_authorization.model;

import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

import com.ctoutweb.example.authentication_authorization.validator.custom.FileNotNull;
import com.ctoutweb.example.authentication_authorization.validator.custom.FileSize;
import com.ctoutweb.example.authentication_authorization.validator.custom.StringLength;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UploadFileRequest {

	@FileNotNull(message="l'ajout du fichier est obligatoire")
	@FileSize(size = 5000000, message = "la taille du fichier ne doit pas dépasser 5 Mo")
	private MultipartFile file;
	
	@NotNull (message = "la description est obligatoire")
	@NotBlank(message = "a description est obligatoire")
	@StringLength(length = 5, message ="la description ne peut pas être inférieur à 5 charactères")
	private String fileDescription;
	
	public UploadFileRequest(MultipartFile file, String fileDescription) {		
		this.file = file;
		this.fileDescription = fileDescription;
	}
	
	/**
	 * @return the file
	 */
	public MultipartFile getFile() {
		return file;
	}
	/**
	 * @param file the file to set
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return fileDescription;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	@Override
	public int hashCode() {
		return Objects.hash(file, fileDescription);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UploadFileRequest other = (UploadFileRequest) obj;
		return Objects.equals(file, other.file) && Objects.equals(fileDescription, other.fileDescription);
	}

	@Override
	public String toString() {
		return "UploadFileRequest [file=" + file + ", fileDescription=" + fileDescription + "]";
	}
}
