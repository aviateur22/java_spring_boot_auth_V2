package com.ctoutweb.example.authentication_authorization.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ctoutweb.example.authentication_authorization.dto.FilePathDto;
import com.ctoutweb.example.authentication_authorization.entity.UserFileEntity;
import com.ctoutweb.example.authentication_authorization.mapper.resultSetToEntity.RoleMapper;
import com.ctoutweb.example.authentication_authorization.mapper.resultSetToEntity.UserMapper;
import com.ctoutweb.example.authentication_authorization.model.RegisterRequest;
import com.ctoutweb.example.authentication_authorization.security.Role;

import com.ctoutweb.example.authentication_authorization.model.User;

@Repository
public class UserRepositoryImp implements UserRepository {
	
	private final JdbcTemplate jdbcTemplate;
	private final int USER_ROLE_ID = 1;
	private final UserMapper userMapper;
	private final RoleMapper roleMapper;
	
	public UserRepositoryImp(JdbcTemplate jdbcTemplate, UserMapper userMapper, RoleMapper roleMapper) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.userMapper = userMapper;
		this.roleMapper = roleMapper;
	}


	@SuppressWarnings("null")
	@Override
	@Transactional
	public Long save(RegisterRequest request) {
		
		KeyHolder userKeyHolder = new GeneratedKeyHolder();
		KeyHolder roleUserKeyHolder = new GeneratedKeyHolder();
		
		Instant createdAt = Instant.now();
		
		String query = "INSERT INTO users (email, password, city_id, created_at, updated_at) VALUES (? , ? , ?, ?, ?)";
		long userRow = jdbcTemplate.update(conn->{
			PreparedStatement pst = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, request.getEmail());
			pst.setString(2, request.getPassword());
			pst.setLong(3, request.getCityId());
			pst.setTimestamp(4, Timestamp.from(createdAt));
			pst.setTimestamp(5, Timestamp.from(createdAt));		
			return pst;
		}, userKeyHolder);
		
		if(userRow > 0 && userKeyHolder.getKeys().size() > 1) {
			int userId = (int)userKeyHolder.getKeys().get("id");
			
			String roleQuery = "INSERT INTO role_user (user_id, role_id, created_at, updated_at) VALUES (? , ? , ?, ?)";
			jdbcTemplate.update(conn->{
				PreparedStatement pst = conn.prepareStatement(roleQuery, Statement.RETURN_GENERATED_KEYS);
				pst.setInt(1, userId);
				pst.setInt(2, USER_ROLE_ID);
				pst.setTimestamp(3, Timestamp.from(createdAt));
				pst.setTimestamp(4, Timestamp.from(createdAt));			
				return pst;
			}, roleUserKeyHolder);
			
			return Long.valueOf(userId);
		}
		
		return null;
	}

	@SuppressWarnings("null")
	@Override
	@Transactional
	public Optional<User> findById(long id) {		
		
		String userQuery = 
				"SELECT u.*, c.id AS cityId, c.name, c.zip, c.created_at AS cCreatedAt, c.updated_at AS cUpdatedAt "
				+ "FROM users AS u "
				+ "JOIN city AS c "
				+ "ON u.city_id=c.id "				
				+ "WHERE u.id=? "
				+ "LIMIT 1";
		
		String roleQuery = 
				"SELECT * FROM role_user "
				+ "JOIN role "
				+ "ON role_user.role_id=role.id "
				+ "WHERE user_id=?";
		
		String filesQuery =  "SELECT * FROM file_user WHERE user_id = ?";
		
		try {			
			
			User user = jdbcTemplate.queryForObject(userQuery, 
				(rs, rowNum)-> userMapper.mapRow(rs), id);
			
			List<com.ctoutweb.example.authentication_authorization.model.Role> userRoles = jdbcTemplate.query(roleQuery,			
				(rs, rowNum)->roleMapper.mapRow(rs)	
			, id);
			
		
			List<Role> roles = userRoles
					.stream()
					.map(e->Role.valueOf(e.getRoleName().toUpperCase())).collect(Collectors.toList());
			
			List<UserFileEntity> files = jdbcTemplate.query(filesQuery, BeanPropertyRowMapper.newInstance(UserFileEntity.class), id);
			
			user.setRoles(roles);
			user.setFiles(files);
			return Optional.of(user);		
			
		} catch (IncorrectResultSizeDataAccessException e) {
			return Optional.empty();
		}	
		
	}

	@Override
	public Optional<User> findByMail(String email) {
		
		String userQuery = 
				"SELECT u.*, c.id AS cityId, c.name, c.zip, c.created_at AS cCreatedAt, c.updated_at AS cUpdatedAt "
				+ "FROM users AS u "
				+ "JOIN city AS c "
				+ "ON u.city_id=c.id "		
				+ "WHERE email=? "
				+ "LIMIT 1";
		
		String roleQuery = 
				"SELECT * FROM role_user "
				+ "JOIN role "
				+ "ON role_user.role_id=role.id "
				+ "WHERE user_id=?";
		
		String filesQuery =  "SELECT * FROM file_user WHERE user_id = ?";
		
		try {
			//User user = jdbcTemplate.queryForObject(userQuery, BeanPropertyRowMapper.newInstance(User.class), email);			
			
			User user = jdbcTemplate.queryForObject(userQuery, 
				(rs, rowNum)-> userMapper.mapRow(rs), email);
			
			List<com.ctoutweb.example.authentication_authorization.model.Role> userRoles = jdbcTemplate.query(roleQuery,			
				(rs, rowNum)->roleMapper.mapRow(rs)	
			, user.getId());
			
			List<UserFileEntity> files = jdbcTemplate.query(filesQuery, BeanPropertyRowMapper.newInstance(UserFileEntity.class),  user.getId());
		
			List<Role> roles = userRoles
					.stream()
					.map(e->Role.valueOf(e.getRoleName().toUpperCase())).collect(Collectors.toList());
			
			user.setRoles(roles);
			user.setFiles(files);
			return Optional.of(user);		
			
		} catch (IncorrectResultSizeDataAccessException e) {
			return Optional.empty();
		}	
	}
	
	
}
