package com.ctoutweb.example.authentication_authorization.model;

import java.util.Objects;

public class Role {
	private Long id;
	private Long userId;
	private Long roleId;
	private String name;
	
	
	public Role() {
		super();
	}

	public Role(Long id, Long userId, Long roleId, String name) {
		super();
		this.id = id;
		this.userId = userId;
		this.roleId = roleId;
		this.name = name;
	}
	
	public Role(RoleBuilder builder) {
		this.id = builder.id;
		this.userId = builder.userId;
		this.roleId = builder.roleId;
		this.name = builder.name;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	public String getRoleName() {
		return name;
	}

	public void setRoleName(String roleName) {
		this.name = roleName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, roleId, userId);
	}
	
	@Override
	public String toString() {
		return "Role [id=" + id + ", userId=" + userId + ", roleId=" + roleId + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(id, other.id) && Objects.equals(roleId, other.roleId)
				&& Objects.equals(userId, other.userId);
	}
	
	public static class RoleBuilder {		
		private Long id;
		private Long userId;
		private Long roleId;
		private String name;
		
		public RoleBuilder id(long id) {
			this.id = id;
			return this;
		}
		
		public RoleBuilder userId(long id) {
			this.userId = id;
			return this;
		}
		
		public RoleBuilder roleId(long id) {
			this.roleId = id;
			return this;
		}
		
		public RoleBuilder name(String name) {
			this.name = name;
			return this;
		}
		
		public Role build() {
			return new Role(this);
		}
	}
	
}
