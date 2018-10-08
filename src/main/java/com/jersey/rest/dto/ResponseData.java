package com.jersey.rest.dto;

import java.io.Serializable;
import java.util.List;

public class ResponseData implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<Employee> employeeList;

	public List<Employee> getEmployeeList() {
		return employeeList;
	}
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
}
