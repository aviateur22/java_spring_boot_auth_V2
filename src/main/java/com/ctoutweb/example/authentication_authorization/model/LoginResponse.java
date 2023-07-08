package com.ctoutweb.example.authentication_authorization.model;

import java.util.Objects;

public class LoginResponse {

	public LoginResponse(String token) {
		super();
		this.token = token;
	}

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public int hashCode() {
		return Objects.hash(token);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginResponse other = (LoginResponse) obj;
		return Objects.equals(token, other.token);
	}

	@Override
	public String toString() {
		return "LoginResponse [token=" + token + "]";
	}
	
}
