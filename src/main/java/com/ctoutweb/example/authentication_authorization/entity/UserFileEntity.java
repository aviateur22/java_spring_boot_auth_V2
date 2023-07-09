package com.ctoutweb.example.authentication_authorization.entity;

import java.util.Date;
import java.util.Objects;

public class UserFileEntity {
	private Long id;
	private String path;
	private String fileName;
	private String fileType;
	private String fileDescription;
	private Long userId;	
	private Date createdAt;
	private Date updatedAt;
	
	public UserFileEntity() {
		
	}
	
	public UserFileEntity(UserFileBuilder builder) {
		this.id = builder.id;
		this.path = builder.path;
		this.fileName = builder.fileName;
		this.fileType = builder.fileType;
		this.fileDescription = builder.fileDescription;
		this.userId = builder.userId;
		this.createdAt = builder.createdAt;
		this.updatedAt = builder.updatedAt;
		
	}
	
	/**
	 * @return the path
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param path the path to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
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
	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}
	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	/**
	 * @return the user_id
	 */
	public Long getUser_id() {
		return userId;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(Long userId) {
		this.userId = userId;
	}
	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}
	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, createdAt, fileName, fileType, path, updatedAt);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserFileEntity other = (UserFileEntity) obj;
		return Objects.equals(userId, other.userId) && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(fileName, other.fileName) && Objects.equals(fileType, other.fileType)
				&& Objects.equals(path, other.path) && Objects.equals(updatedAt, other.updatedAt);
	}

	@Override
	public String toString() {
		return "UserFile [path=" + path + ", fileName=" + fileName + ", fileType=" + fileType + ", User_id=" + userId
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	public static class UserFileBuilder {
		private Long id;
		private String path;
		private String fileName;
		private String fileType;
		private String fileDescription;
		private Long userId;
		private Date createdAt;
		private Date updatedAt;
		
		public UserFileBuilder id(Long id) {
			this.id = id;
			return this;			
		}
		
		public UserFileBuilder path(String path) {
			this.path = path;
			return this;			
		}
		
		public UserFileBuilder fileName(String fileName) {
			this.fileName = fileName;
			return this;			
		}
		
		public UserFileBuilder fileType(String fileType) {
			this.fileType = fileType;
			return this;			
		}
		
		public UserFileBuilder fileDescription(String fileDescription) {
			this.fileDescription = fileDescription;
			return this;
		}
		
		public UserFileBuilder Userid(Long userId) {
			this.userId = userId;
			return this;			
		}
		
		public UserFileBuilder createdAt(Date date) {
			this.createdAt = date;
			return this;
		}
		
		public UserFileBuilder updatedAt(Date date) {
			this.updatedAt = date;
			return this;
		}
		
		public UserFileEntity build() {
			return new UserFileEntity(this);
		}
	}
	
}
