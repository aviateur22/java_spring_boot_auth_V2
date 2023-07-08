package com.ctoutweb.example.authentication_authorization.model;

import java.util.Objects;

public class ErrorResponse {
	private String errorMessage;

	public ErrorResponse(String message) {
		super();
		this.errorMessage = message;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public int hashCode() {
		return Objects.hash(errorMessage);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorResponse other = (ErrorResponse) obj;
		return Objects.equals(errorMessage, other.errorMessage);
	}

	@Override
	public String toString() {
		return "ErrorResponse [errorMessage=" + errorMessage + "]";
	}
	
}
