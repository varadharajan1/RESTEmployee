package com.jersey.rest.dto;

import java.io.Serializable;

public class Employee implements Serializable{
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String designation;
	private String email;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", designation=" + designation + ", email=" + email + "]";
	}

}
