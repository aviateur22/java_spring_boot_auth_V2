package com.ctoutweb.example.authentication_authorization.entity;

import java.util.Date;
import java.util.Objects;

public class RoleEntity {
	private long id;
	private String name;
	private Date createdAt;
	private Date updatedAt;
	
	public RoleEntity() {
		
	}
	
	public RoleEntity(RoleBuilder builder) {
		this.id= builder.id;
		this.name = builder.name;
		this.createdAt = builder.createdAt;
		this.updatedAt = builder.updatedAt;
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
		return Objects.hash(createdAt, id, name, updatedAt);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleEntity other = (RoleEntity) obj;
		return Objects.equals(createdAt, other.createdAt) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(updatedAt, other.updatedAt);
	}
	@Override
	public String toString() {
		return "RoleEntity [id=" + id + ", name=" + name + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ "]";
	}
	
	public static class RoleBuilder  {
		
		private long id;
		private String name;
		private Date createdAt;
		private Date updatedAt;
		
		public RoleBuilder id(Long id) {
			this.id = id;
			return this;
		}
		
		public RoleBuilder name(String name) {
			this.name = name;
			return this;
		}
		
		
		public RoleBuilder createdAt(Date date) {
			this.createdAt = date;
			return this;
		}
		
		public RoleBuilder updatedAt(Date date) {
			this.updatedAt = date;
			return this;
		}
		
		public RoleEntity build() {
			return new RoleEntity(this);
		}
	}
}
