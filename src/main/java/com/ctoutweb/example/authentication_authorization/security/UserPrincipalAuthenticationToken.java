package com.ctoutweb.example.authentication_authorization.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class UserPrincipalAuthenticationToken extends AbstractAuthenticationToken {
	
	private final UserPrincipal userPrincipal;

	public UserPrincipalAuthenticationToken(UserPrincipal userPrincipal) {
		super(userPrincipal.getAuthorities());
		this.userPrincipal = userPrincipal;
		setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {		
		return userPrincipal;
	}	

}
