package com.ctoutweb.example.authentication_authorization.model;

import java.util.Objects;

import com.ctoutweb.example.authentication_authorization.validator.custom.InputMatch;
import com.ctoutweb.example.authentication_authorization.validator.custom.PasswordConstraint;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@InputMatch(first="password", second ="confirmPassword", message ="les ")
public class RegisterRequest {
	@NotNull(message = "Votre email est obligatoire")
	@NotBlank(message = "Votre email est obligatoire")
	@Email(message = "Votre email n'est pas au bon format")
	private String email;

	@NotNull(message = "Le mot de passe est obligatoire")
	@NotBlank(message = "Le mot de passe est obligatoire")
	@PasswordConstraint(message = "Le mot de passe n'est pas valide")
	private String password;
	
	@NotNull(message = "La confirmation du mot de passe est obligatoire")
	@NotBlank(message = "La confirmation du mot de passe est obligatoire")
	private String confirmPassword;
	
	@NotNull(message = "La ville est obligatoire")
	@Min(value = 1, message="sss")
	private Long cityId;
	
	public RegisterRequest(String email, String password, Long cityId) {
		super();
		this.email = email;
		this.password = password;	
		this.cityId = cityId;		
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(email, password);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegisterRequest other = (RegisterRequest) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password);
	}
	@Override
	public String toString() {
		return "RegisterRequest [email=" + email + ", password=" + password + "]";
	}
	
}
