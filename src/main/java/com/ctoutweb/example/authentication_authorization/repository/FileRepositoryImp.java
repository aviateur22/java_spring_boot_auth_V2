package com.ctoutweb.example.authentication_authorization.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ctoutweb.example.authentication_authorization.entity.UserFileEntity;

@Repository
public class FileRepositoryImp implements FileRepository {
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final JdbcTemplate jdbcTemplate;
	

	public FileRepositoryImp(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
		super();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.jdbcTemplate = jdbcTemplate;
	}

	@SuppressWarnings("null")
	@Override
	@Transactional
	public int save(UserFileEntity fileData) {
		KeyHolder fileKeyHolder = new GeneratedKeyHolder();
		SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(fileData);
		String query = "INSERT INTO file_user (path, file_name, file_type, file_description, user_id,created_at, updated_at) VALUES (:path, :fileName, :fileType, :fileDescription, :userId, :createdAt, :updatedAt)";
		int insertRow = namedParameterJdbcTemplate.update(query, sqlParam, fileKeyHolder);
		
		if(insertRow > 0 && fileKeyHolder.getKeys().size() > 1) {
			int fileId = (int)fileKeyHolder.getKeys().get("id");
			System.out.println(fileId);
			return fileId;
		}
		throw new RuntimeException("oupsss");
	}

	@Override
	public int deleteById(Long id) {
		String query = "DELETE FROM file_user WHERE id = ?";
		int deleteRow = jdbcTemplate.update(query, id);		
		return deleteRow;
	}

	@Override
	public void deleteByUserId(Long id) {
		String query = "DELETE FROM file_user WHERE user_id = ?";
		jdbcTemplate.update(query, id);
	}

	@Override
	public Optional<UserFileEntity> findFileById(Long id) {
		try {
			String query = "SELECT * FROM file_user WHERE id = ?";
			UserFileEntity userFile = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(UserFileEntity.class), id);		
			return Optional.of(userFile);
		} catch (IncorrectResultSizeDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<List<UserFileEntity>> findFileByUserId(Long id) {
		try {
			String query = "SELECT * FROM file_user WHERE user_id = ?";
			List<UserFileEntity> userFile = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(UserFileEntity.class), id);		
			return Optional.of(userFile);
		} catch (IncorrectResultSizeDataAccessException e) {
			return Optional.empty();
		}
	}

}
