package com.ctoutweb.example.authentication_authorization.model;

import java.util.Objects;

public class UploadResponse {

	private String message;
	
	public UploadResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		return Objects.hash(message);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UploadResponse other = (UploadResponse) obj;
		return Objects.equals(message, other.message);
	}

	@Override
	public String toString() {
		return "UploadResponse [message=" + message + "]";
	}
	

}
