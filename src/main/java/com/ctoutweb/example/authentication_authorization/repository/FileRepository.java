package com.ctoutweb.example.authentication_authorization.repository;

import java.util.List;
import java.util.Optional;

import com.ctoutweb.example.authentication_authorization.entity.UserFileEntity;

public interface FileRepository {	
	int save(UserFileEntity data);
	int deleteById(Long id);
	void deleteByUserId(Long id);
	Optional<UserFileEntity> findFileById(Long id);
	Optional<List<UserFileEntity>> findFileByUserId(Long id);
}
