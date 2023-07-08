package com.ctoutweb.example.authentication_authorization.service;

import org.springframework.stereotype.Service;

import com.ctoutweb.example.authentication_authorization.exception.UserNotFindException;
import com.ctoutweb.example.authentication_authorization.model.LoginResponse;
import com.ctoutweb.example.authentication_authorization.model.User;
import com.ctoutweb.example.authentication_authorization.repository.UserRepositoryImp;

@Service
public class UserService {
		
	private final UserRepositoryImp userRepository;
	
	public UserService(UserRepositoryImp userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public <T> User getUserInformation(T param) {
		
		String dataType = param.getClass().getName();
		
		
		
		switch(dataType){
			case "java.lang.Long": {
				return userRepository.findById((Long) param).orElseThrow(()->new UserNotFindException("utilisateur absent de la base de données"));
			}
			
			case "java.lang.String": {
				System.out.println(param);
				return userRepository.findByMail((String) param).orElseThrow(()->new UserNotFindException("utilisateur absent de la base de données"));
			}
			
			default: throw new RuntimeException("Impossible de récupérer l'utilisateur");
				
		}
	}
	
	public LoginResponse login() {
		return null;
		
	}
}
