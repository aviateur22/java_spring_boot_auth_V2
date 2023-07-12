package com.ctoutweb.example.authentication_authorization.dto;

import java.util.Objects;

public class FilePathDto {
	private Long id;
	private String description;	
	private String path;
	
	public FilePathDto() {
		
	}
	
	public FilePathDto(long id, String description, String path) {
		this.id = id;
		this.description = description;
		this.setPath(path);
	}
	
	public FilePathDto(FileBuilder builder) {
		this.id = builder.id;
		this.description = builder.description;
		this.setPath(builder.path);
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}	
	
	@Override
	public int hashCode() {
		return Objects.hash(description, id, path);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilePathDto other = (FilePathDto) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(path, other.path);
	}


	@Override
	public String toString() {
		return "FilePathDto [id=" + id + ", description=" + description + ", path=" + path + "]";
	}


	public static class FileBuilder {
		private Long id;
		private String description;	
		private String path;
		
		public FileBuilder id(Long id) {
			this.id = id;
			return this;
		}
		
		public FileBuilder description(String description) {
			this.description = description;
			return this;
		}
		
		public FileBuilder path(String path) {
			this.path = path;
			return this;
		}
		
		
		public FilePathDto build() {
			return new FilePathDto(this);
		}
	}
}
