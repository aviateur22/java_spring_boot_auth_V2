package com.ctoutweb.example.authentication_authorization.model;

import java.util.Date;
import java.util.Objects;

public class City {
	private Long id;
	private String name;
	private String zip;
	private Date createdAt;
	private Date updatedAt;
	
	public City() {
		
	}
	
	public City(Long id, String name, String zip, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.zip = zip;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public City(CityBuilder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.zip = builder.zip;
		this.createdAt =builder. createdAt;
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
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}
	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, name, zip);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(zip, other.zip);
	}
	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", zip=" + zip + "]";
	}
	
	public static class CityBuilder {
		private Long id;
		private String name;
		private String zip;
		private Date createdAt;
		private Date updatedAt;
		
		public CityBuilder id(long id) {
			this.id = id;			
			return this;
		}
		
		public CityBuilder name(String name) {
			this.name = name;			
			return this;
		}
		
		public CityBuilder zip(String zip) {
			this.zip = zip;			
			return this;
		}
		
		public CityBuilder created(Date date) {
			this.createdAt = date;			
			return this;
		}
		
		public CityBuilder updatedAt(Date date) {
			this.updatedAt = date;			
			return this;
		}
		
		public City build() {
			return new City(this);
		}
	}
	

}
