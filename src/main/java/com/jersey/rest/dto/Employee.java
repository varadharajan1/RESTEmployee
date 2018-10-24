package com.jersey.rest.dto;

import java.io.Serializable;

public class Employee implements Serializable{
	private static final long serialVersionUID = 1L;

	private String empNo;
	private String name;
	private String designation;
	private String email;

	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
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
		return "Employee [empNo=" + empNo + ", name=" + name + ", designation=" + designation + ", email=" + email
				+ "]";
	}

}
