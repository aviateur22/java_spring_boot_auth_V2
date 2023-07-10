package com.ctoutweb.example.authentication_authorization.model;

import java.util.Objects;

public class DeleteFileRequest {
	private Long id;
	
	public DeleteFileRequest() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeleteFileRequest other = (DeleteFileRequest) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "DeleteFile [id=" + id + "]";
	}

}
