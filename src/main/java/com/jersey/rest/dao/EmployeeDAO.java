package com.jersey.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jersey.rest.core.DataSourceListener;
import com.jersey.rest.core.EmployeeException;
import com.jersey.rest.dto.Employee;
import com.jersey.rest.util.Constants;
import com.jersey.rest.util.Validator;

public class EmployeeDAO {

	private static final Logger logger = Logger.getLogger(EmployeeDAO.class.getName());
	
	private String insertEmployee = "INSERT INTO EMPLOYEE (EMP_NO, EMP_NAME, EMAIL, DESIGNATION) VALUES (?,?,?,?)";
	private String updateEmployee = "UPDATE EMPLOYEE SET EMP_NAME=? , EMAIL=? , DESIGNATION=? WHERE EMP_NO=?";
	private String deleteEmployee = "DELETE FROM EMPLOYEE WHERE EMP_NO=?";
	private String selectEmployee = "SELECT (EMP_NO, EMP_NAME, EMAIL, DESIGNATION) FROM EMPLOYEE WHERE EMP_NO=?";
	private String selectAll = "SELECT (EMP_NO, EMP_NAME, EMAIL, DESIGNATION) FROM EMPLOYEE";
	
	public int insertEmployee(Employee emp) {
		logger.log(Level.INFO, "EmployeeDAO: insertEmployee() executed.");
		
		Connection conn = null;
	    PreparedStatement ps = null;
	    String empNo = null;
	    int result = 0;
	    try {
		    if(emp != null) {
		    	empNo = emp.getEmpNo();
			    if (Validator.isEmpty(empNo)) {
			    	throw new EmployeeException(Constants.ERROR_INVALID_ID);
			    }
				conn = DataSourceListener.getPostGresDS().getConnection();

				logger.log(Level.INFO, "EmployeeDAO: insert SQL: {0} ", insertEmployee);
				
				ps = conn.prepareStatement(insertEmployee);
				ps.setString(1, emp.getEmpNo());
				ps.setString(2, emp.getName());
				ps.setString(3, emp.getEmail());
				ps.setString(4, emp.getDesignation());

				result = ps.executeUpdate();

				logger.log(Level.INFO, "EmployeeDAO: {0} of row(s) inserted.", result);
		    }else {
				logger.log(Level.INFO, Constants.ERROR_INVALID_INPUT);
		    	throw new EmployeeException(Constants.ERROR_INVALID_INPUT);
		    }
	    } catch (Exception e) {
			logger.log(Level.INFO, e.getMessage(), e);
			throw new EmployeeException(e.getMessage());
	    }
	    finally {
			DAOUtil.closePreparedStatement(ps);
			DAOUtil.close(conn);
	    }
	    logger.log(Level.INFO, "EmployeeDAO: insertEmployee() ended.");
	    
	    return result;
	}
	
	public int updateEmployee(Employee emp) {
		logger.log(Level.INFO, "EmployeeDAO: updateEmployee() executed.");
		
		Connection conn = null;
	    PreparedStatement ps = null;
	    String empNo = null;
	    int result = 0;
	    try {
		    if(emp != null) {
		    	empNo = emp.getEmpNo();
			    if (Validator.isEmpty(empNo)) {
			    	throw new EmployeeException(Constants.ERROR_INVALID_ID);
			    }
				conn = DataSourceListener.getPostGresDS().getConnection();

				logger.log(Level.INFO, "EmployeeDAO: update SQL: {0} ", updateEmployee);
				
				ps = conn.prepareStatement(updateEmployee);
				ps.setString(1, emp.getName());
				ps.setString(2, emp.getEmail());
				ps.setString(3, emp.getDesignation());
				ps.setString(4, emp.getEmpNo());

				result = ps.executeUpdate();

				logger.log(Level.INFO, "EmployeeDAO: {0} of row(s) updated.", result);
		    }else {
				logger.log(Level.INFO, Constants.ERROR_INVALID_INPUT);
		    	throw new EmployeeException(Constants.ERROR_INVALID_INPUT);
		    }
	    } catch (Exception e) {
			logger.log(Level.INFO, e.getMessage(), e);
			throw new EmployeeException(e.getMessage());
	    }
	    finally {
			DAOUtil.closePreparedStatement(ps);
			DAOUtil.close(conn);
	    }
	    logger.log(Level.INFO, "EmployeeDAO: updateEmployee() ended.");
	    
	    return result;
	}

	public int deleteEmployee(String empNo) {
		logger.log(Level.INFO, "EmployeeDAO: deleteEmployee() executed.");
		
		Connection conn = null;
	    PreparedStatement ps = null;
	    int result = 0;
	    try {
		    if (Validator.isEmpty(empNo)) {
		    	throw new EmployeeException(Constants.ERROR_INVALID_ID);
		    }
			conn = DataSourceListener.getPostGresDS().getConnection();

			logger.log(Level.INFO, "EmployeeDAO: insert SQL: {0} ", deleteEmployee);
			
			ps = conn.prepareStatement(deleteEmployee);
			ps.setString(1, empNo);

			result = ps.executeUpdate();

			logger.log(Level.INFO, "EmployeeDAO: {0} of row(s) deleted.", result);
	    } catch (Exception e) {
			logger.log(Level.INFO, e.getMessage(), e);
			throw new EmployeeException(e.getMessage());
	    }
	    finally {
			DAOUtil.closePreparedStatement(ps);
			DAOUtil.close(conn);
	    }
	    logger.log(Level.INFO, "EmployeeDAO: deleteEmployee() ended.");
	    
	    return result;
	}

	public Employee selectEmployee(String empNo) {
		logger.log(Level.INFO, "EmployeeDAO: selectEmployee() executed.");
		
		Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    Employee result = null;
	    try {
		    if (Validator.isEmpty(empNo)) {
		    	throw new EmployeeException(Constants.ERROR_INVALID_ID);
		    }
			conn = DataSourceListener.getPostGresDS().getConnection();

			logger.log(Level.INFO, "EmployeeDAO: select SQL: {0} ", selectEmployee);
			
			ps = conn.prepareStatement(selectEmployee);
			ps.setString(1, empNo);

			rs = ps.executeQuery();
			if (rs.next()) {
				result = populateItems(rs);
			    logger.log(Level.INFO, "EmployeeDAO: {0}", result);
			}
	    } catch (Exception e) {
			logger.log(Level.INFO, e.getMessage(), e);
			throw new EmployeeException(e.getMessage());
	    }
	    finally {
			DAOUtil.close(rs);
			DAOUtil.closePreparedStatement(ps);
			DAOUtil.close(conn);
	    }
	    logger.log(Level.INFO, "EmployeeDAO: selectEmployee() ended.");
	    
	    return result;
	}

	public List<Employee> selectAllEmployees() {
		logger.log(Level.INFO, "EmployeeDAO: selectAllEmployees() executed.");
		
		Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<Employee> employeeList = new ArrayList<>();
	    try {
			conn = DataSourceListener.getPostGresDS().getConnection();

			logger.log(Level.INFO, "EmployeeDAO: selectAll SQL: {0} ", selectAll);
			
			ps = conn.prepareStatement(selectAll);

			rs = ps.executeQuery();
			while (rs.next()) {
				employeeList.add(populateItems(rs));
			}
		    logger.log(Level.INFO, "EmployeeDAO: employee list: {0}", employeeList.size());
	    } catch (Exception e) {
			logger.log(Level.INFO, e.getMessage(), e);
			throw new EmployeeException(e.getMessage());
	    }
	    finally {
			DAOUtil.close(rs);
			DAOUtil.closePreparedStatement(ps);
			DAOUtil.close(conn);
	    }
	    logger.log(Level.INFO, "EmployeeDAO: selectAllEmployees() ended.");
	    
	    return employeeList;
	}

	private Employee populateItems(ResultSet rs) throws SQLException{
		Employee emp = new Employee();

		emp.setEmpNo(rs.getString("EMP_NO"));
		emp.setName(rs.getString("EMP_NAME"));
		emp.setEmail(rs.getString("EMAIL"));
		emp.setDesignation(rs.getString("DESIGNARION"));

		return emp;
	}
}
