package com.ctoutweb.example.authentication_authorization.model;

import java.util.Objects;

public class RegisterResponse {
	private String message;

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	public RegisterResponse(String message) {
		super();
		this.message = message;
	}

	/**
	 * @param message the message to set
	 */
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
		RegisterResponse other = (RegisterResponse) obj;
		return Objects.equals(message, other.message);
	}

	@Override
	public String toString() {
		return "RegisterResponse [message=" + message + "]";
	}
}
