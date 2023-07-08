package com.ctoutweb.example.authentication_authorization.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ctoutweb.example.authentication_authorization.model.User;
import com.ctoutweb.example.authentication_authorization.service.UserService;

@Service
public class UserPrincipalDetailService implements UserDetailsService {
	
	private final UserService userService;

	public UserPrincipalDetailService(UserService userService) {
		super();
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		
		User user = userService.getUserInformation(username);		
		
		return new UserPrincipal.UserBuilder()
			.id(user.getId())
			.email(user.getEmail())
			.password(user.getPassword())
			.authorities(convertListRoleToListOfSimpleGrantedtAuthorities(user))
			.build();
	}
	
	private Collection<SimpleGrantedAuthority> convertListRoleToListOfSimpleGrantedtAuthorities(User user){
		
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for(Role role: user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.name()));
		}
		
		return authorities;
	}

}
