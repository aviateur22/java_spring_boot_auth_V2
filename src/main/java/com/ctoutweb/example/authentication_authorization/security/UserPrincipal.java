package com.ctoutweb.example.authentication_authorization.security;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {
	
	private Long id;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	private Date createdAt;
	private Date updatedAt;

	public UserPrincipal(UserBuilder builder) {
		super();
		this.id = builder.id;
		this.email = builder.email;
		this.password = builder.password;
		this.authorities = builder.authorities;
		this.createdAt = builder.createdAt;
		this.updatedAt = builder.updatedAt;
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

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public static class UserBuilder {
		private Long id;
		private String email;
		private String password;
		private Collection<? extends GrantedAuthority> authorities;
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
		
		public UserBuilder roles(Collection<? extends GrantedAuthority> authorities) {
			this.authorities = authorities;
			return this;
		}
		
		public UserBuilder createdAt(Date date) {
			this.createdAt = date;
			return this;
		}
		
		public UserBuilder authorities(Collection<? extends GrantedAuthority> authorities){
			this.authorities = authorities;
			return this;
		}
		
		public UserBuilder updatedAt(Date date) {
			this.updatedAt = date;
			return this;
		}
		
		public UserPrincipal build() {
			return new UserPrincipal(this);
		}
	}

}
