package com.ctoutweb.example.authentication_authorization.model;

import java.util.Objects;

public class FileResponse {
	private Long id;
	private String description;
	private String fileName;	
	private String fileUri;
	private String fileType;
	
	public FileResponse(long id, String description, String fileName, String fileUri, String fileType) {
		this.id = id;
		this.description = description;
		this.fileName = fileName;
		this.fileUri = fileUri;
		this.fileType = fileType;
	}
	
	public FileResponse(FileBuilder builder) {
		this.id = builder.id;
		this.description = builder.description;
		this.fileName = builder.fileName;
		this.fileUri = builder.fileUri;
		this.fileType = builder.fileType;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileUri() {
		return fileUri;
	}

	public void setFileUri(String fileUri) {
		this.fileUri = fileUri;
	}

	
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fileName, id, fileUri, fileType);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileResponse other = (FileResponse) obj;
		return Objects.equals(fileName, other.fileName) && Objects.equals(id, other.id)
				&& Objects.equals(fileUri, other.fileUri);
	}
	@Override
	public String toString() {
		return "FileRequestResponse [id=" + id + ", description=" + description + ", fileName=" + fileName + ", url="
				+ fileUri + "]";
	}
	
	public static class FileBuilder {
		private Long id;
		private String description;
		private String fileName;	
		private String fileUri;
		private String fileType;
		
		public FileBuilder id(Long id) {
			this.id = id;
			return this;
		}
		
		public FileBuilder description(String description) {
			this.description = description;
			return this;
		}
		
		public FileBuilder fileName(String fileName) {
			this.fileName = fileName;
			return this;
		}
		
		public FileBuilder fileUri(String fileUri) {
			this.fileUri = fileUri;
			return this;
		}
		
		public FileBuilder fileType(String fileType) {
			this.fileType = fileType;
			return this;
		}
		
		public FileResponse build() {
			return new FileResponse(this);
		}
	}
}
