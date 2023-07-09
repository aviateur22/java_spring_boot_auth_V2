package com.ctoutweb.example.authentication_authorization.entity;

import java.util.Date;
import java.util.Objects;

public class UserEntity {
	private Long id;
	private String email;
	private String password;
	private Long cityId;
	private Date createdAt;
	private Date updatedAt;
	
	public UserEntity(UserBuilder builder) {
		super();
		this.id = builder.id;
		this.email = builder.email;
		this.password = builder.password;		
		this.cityId = builder.cityId; 
		this.createdAt = builder.createdAt;
		this.updatedAt = builder.updatedAt;
	}

	public UserEntity() {
		
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
	
	public Long getCity() {
		return cityId;
	}

	public void setCity(Long cityId) {
		this.cityId = cityId;
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
		return Objects.hash(createdAt, email, id, password, updatedAt);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntity other = (UserEntity) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(password, other.password)
				&& Objects.equals(updatedAt, other.updatedAt);
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}
	
	public static class UserBuilder {
		private Long id;
		private String email;
		private String password;		
		private Long cityId;
		private Date createdAt;
		private Date updatedAt;
		
		
		public UserBuilder id(long id) {
			this.id = id;
			return this;
		}
		
		public UserBuilder email(String email) {
			this.email = email;
			return this;
		}
		
		public UserBuilder password(String password) {
			this.password = password;
			return this;
		}		
		
		
		public UserBuilder cityId(Long cityId) {
			this.cityId = cityId;
			return this;
		}
		
		public UserBuilder createdAt(Date date) {
			this.createdAt = date;
			return this;
		}
		
		public UserBuilder updatedAt(Date date) {
			this.updatedAt = date;
			return this;
		}
		
		public UserEntity build() {
			return new UserEntity(this);
		}
	}
}
