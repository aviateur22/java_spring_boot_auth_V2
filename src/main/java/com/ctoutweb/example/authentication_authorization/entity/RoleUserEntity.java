package com.ctoutweb.example.authentication_authorization.entity;

import java.util.Date;
import java.util.Objects;

public class RoleUserEntity {
	private Long id;
	private Long userId;
	private Long roleId;
	private Date createdAt;
	private Date updatedAt;
	
	public RoleUserEntity(RoleUserBuilder builder) {
		this.id = builder.id;
		this.userId = builder.userId;
		this.roleId = builder.roleId;
		this.createdAt = builder.createdAt;
		this.updatedAt = builder.updatedAt;
		
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
	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}
	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Override
	public int hashCode() {
		return Objects.hash(createdAt, id, roleId, updatedAt, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleUserEntity other = (RoleUserEntity) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(id, other.id)
				&& Objects.equals(roleId, other.roleId) && Objects.equals(updatedAt, other.updatedAt)
				&& Objects.equals(userId, other.userId);
	}
	@Override
	public String toString() {
		return "RoleUserEntity [id=" + id + ", userId=" + userId + ", roleId=" + roleId + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}
	
	public static class RoleUserBuilder {
		private Long id;
		private Long userId;
		private Long roleId;
		private Date createdAt;
		private Date updatedAt;
		
		public RoleUserBuilder id(Long id) {
			this.id = id;
			return this;
		}
		
		public RoleUserBuilder userId(Long userId) {
			this.userId = userId;
			return this;
		}
		
		public RoleUserBuilder roleId(Long roleId) {
			this.roleId = roleId;
			return this;
		}
		
		public RoleUserBuilder createdAt(Date date) {
			this.createdAt = date;
			return this;
		}
		
		public RoleUserBuilder updatedAt(Date date) {
			this.updatedAt = date;
			return this;
		}		

		public RoleUserEntity build() {
			return new RoleUserEntity(this);
		}
	}
}
