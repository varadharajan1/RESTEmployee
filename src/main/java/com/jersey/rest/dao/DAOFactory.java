package com.jersey.rest.dao;

public class DAOFactory {

	private static final DAOFactory factory = new DAOFactory();
	private EmployeeDAO employeeDAO;
  
	private DAOFactory() { }
	
	public static DAOFactory getInstance() {
		return factory;
	}

	public EmployeeDAO getEmployeeDAO() {
		if(employeeDAO == null) {
			employeeDAO = new EmployeeDAO();
		}
		return employeeDAO;
	}
}
