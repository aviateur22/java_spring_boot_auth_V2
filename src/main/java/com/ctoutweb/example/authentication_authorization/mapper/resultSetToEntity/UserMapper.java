package com.ctoutweb.example.authentication_authorization.mapper.resultSetToEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.ctoutweb.example.authentication_authorization.model.City;
import com.ctoutweb.example.authentication_authorization.model.User;

@Component
public class UserMapper {

	public User mapRow(ResultSet rs) throws SQLException {
		City city = new City.CityBuilder()
				.id(rs.getLong("cityId"))
				.name(rs.getString("name"))
				.zip(rs.getString("zip"))
				.created(rs.getTimestamp("cCreatedAt"))
				.updatedAt(rs.getTimestamp("cUpdatedAt"))
				.build();
		
		User user = new User.UserBuilder()
				.id(rs.getLong("id"))
				.email(rs.getString("email"))
				.password(rs.getString("password"))				
				.city(city)
				.createdAt(rs.getTimestamp("created_at"))
				.updatedAt(rs.getTimestamp("updated_at"))
				.build();
		
		return user;
	}
}
