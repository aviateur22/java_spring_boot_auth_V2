package com.ctoutweb.example.authentication_authorization.mapper.resultSetToEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.ctoutweb.example.authentication_authorization.model.Role;

@Component
public class RoleMapper {
	
	public Role mapRow(ResultSet rs) throws SQLException {
		return new Role.RoleBuilder()
				.id(rs.getLong("id")) 
				.userId(rs.getLong("user_id"))
				.roleId(rs.getLong("role_id"))
				.name(rs.getString("name"))
				.build();
				
	}
}
