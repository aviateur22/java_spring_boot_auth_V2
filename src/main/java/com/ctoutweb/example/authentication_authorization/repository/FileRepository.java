package com.ctoutweb.example.authentication_authorization.repository;

import java.util.List;
import java.util.Optional;

import com.ctoutweb.example.authentication_authorization.entity.UserFileEntity;

public interface FileRepository {	
	Long save(UserFileEntity data);
	Long deleteById(Long id);
	Long deleteByUserId(Long id);
	Optional<UserFileEntity> getFileById(Long id);
	Optional<List<UserFileEntity>> getFileByUserId(Long id);
}
