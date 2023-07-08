package com.ctoutweb.example.authentication_authorization.security.token;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ctoutweb.example.authentication_authorization.security.UserPrincipal;

@Component
public class JwtToUserPrincipalConverter {
	
	public UserPrincipal convert(DecodedJWT jwt) {
		
		List<SimpleGrantedAuthority> authorities = extractAuthoritiesFromClaim(jwt);
		
		return new UserPrincipal.UserBuilder()
		.id(jwt.getClaim("id").asLong())
		.email(jwt.getSubject())
		.authorities(authorities)
		.build();
		
	}
	
	private List<SimpleGrantedAuthority> extractAuthoritiesFromClaim(DecodedJWT jwt){
		var claim = jwt.getClaim("authorities");	
		
		if(claim.isNull() || claim.isMissing()) return List.of();
		
		return claim.asList(SimpleGrantedAuthority.class);
		
	}
	
	

}
