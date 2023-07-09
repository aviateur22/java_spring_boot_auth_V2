package com.ctoutweb.example.authentication_authorization.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ctoutweb.example.authentication_authorization.entity.UserFileEntity;

@Repository
public class FileRepositoryImp implements FileRepository {

	@Override
	public Long save(UserFileEntity data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long deleteByUserId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<UserFileEntity> getFileById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<List<UserFileEntity>> getFileByUserId(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
