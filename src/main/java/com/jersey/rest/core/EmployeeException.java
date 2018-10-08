package com.jersey.rest.core;

public class EmployeeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	  
	public EmployeeException(String message) {
		super(message);
	}
}
