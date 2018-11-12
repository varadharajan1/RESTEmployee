package com.jersey.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jersey.rest.dao.DAOFactory;
import com.jersey.rest.dto.Employee;
import com.jersey.rest.dto.EmployeeResponse;
import com.jersey.rest.dto.ResponseData;
import com.jersey.rest.dto.ResponseDetails;
import com.jersey.rest.util.Constants;
import com.jersey.rest.util.Validator;

public class EmployeeServiceImpl {
	private static final Logger logger = Logger.getLogger(EmployeeServiceImpl.class.getName());
	
	public EmployeeResponse create(Employee request) {
		EmployeeResponse employeeResponse = new EmployeeResponse();
		logger.log(Level.INFO, "EmployeeServiceImpl: create() [{0}]", request);
		try {
			if(request != null) {
				if (Validator.isEmpty(request.getEmpNo()) && Validator.isEmpty(request.getName())) {
					return generateFailureResponse(generateFailureDetails("1", Constants.ERROR_INVALID_INPUT));
				}
				logger.log(Level.INFO, "EmployeeServiceImpl: create() invoked for the employee: [{0}]", request.getName());
				
				int result = DAOFactory.getInstance().getEmployeeDAO().insertEmployee(request);
				
				String message = result + " row(s) inserted successfully.";
				
				ResponseData data = new ResponseData();
				employeeResponse.setData(data);
				employeeResponse.setDetails(generateSuccessDetails(message));
				
				logger.log(Level.INFO, "EmployeeServiceImpl: create(): {0}", message);
			}else {
				return generateFailureResponse(generateFailureDetails("1", Constants.ERROR_INVALID_INPUT));
			}
		} catch (Exception e) {
			logger.log(Level.INFO, e.getMessage(), e);
			return generateFailureResponse(generateFailureDetails("128", e.getMessage()));
		}
		logger.log(Level.INFO, "EmployeeServiceImpl: create: ended");
		return employeeResponse;
	}

	public EmployeeResponse update(Employee request) {
		EmployeeResponse employeeResponse = new EmployeeResponse();
		logger.log(Level.INFO, "EmployeeServiceImpl: update() [{0}]", request);
		try {
			if(request != null) {
				if (Validator.isEmpty(request.getEmpNo()) && Validator.isEmpty(request.getName())) {
					return generateFailureResponse(generateFailureDetails("1", Constants.ERROR_INVALID_INPUT));
				}
				logger.log(Level.INFO, "EmployeeServiceImpl: update() invoked for the employee: [{0}]", request.getName());
				
				int result = DAOFactory.getInstance().getEmployeeDAO().updateEmployee(request);
				
				String message = result + " row(s) updated successfully.";
				
				ResponseData data = new ResponseData();
				employeeResponse.setData(data);
				employeeResponse.setDetails(generateSuccessDetails(message));
				
				logger.log(Level.INFO, "EmployeeServiceImpl: update(): {0}", message);
			}else {
				return generateFailureResponse(generateFailureDetails("1", Constants.ERROR_INVALID_INPUT));
			}
		} catch (Exception e) {
			logger.log(Level.INFO, e.getMessage(), e);
			return generateFailureResponse(generateFailureDetails("128", e.getMessage()));
		}
		logger.log(Level.INFO, "EmployeeServiceImpl: update: ended");
		return employeeResponse;
	}

	public EmployeeResponse read(String empNo) {
		EmployeeResponse employeeResponse = new EmployeeResponse();
		try {
			if (Validator.isEmpty(empNo)) {
				return generateFailureResponse(generateFailureDetails("1", Constants.ERROR_INVALID_INPUT));
			}
			logger.log(Level.INFO, "EmployeeServiceImpl: read() invoked for the employee: [{0}]", empNo);
			
			Employee result = DAOFactory.getInstance().getEmployeeDAO().selectEmployee(empNo);
			List<Employee> employeeList = new ArrayList<>();
			employeeList.add(result);
			
			String message = employeeList.size() + " row(s) retrieved successfully.";
			
			ResponseData data = new ResponseData();
			data.setEmployeeList(employeeList);
			employeeResponse.setData(data);
			employeeResponse.setDetails(generateSuccessDetails(message));
			
			logger.log(Level.INFO, "EmployeeServiceImpl: read(): {0}", employeeList.size());
		} catch (Exception e) {
			logger.log(Level.INFO, e.getMessage(), e);
			return generateFailureResponse(generateFailureDetails("128", e.getMessage()));
		}
		logger.log(Level.INFO, "EmployeeServiceImpl: read: ended");
		return employeeResponse;
	}

	public EmployeeResponse readAll() {
		EmployeeResponse employeeResponse = new EmployeeResponse();
		try {
			logger.log(Level.INFO, "EmployeeServiceImpl: readAll() invoked. ");
			
			List<Employee> employeeList = DAOFactory.getInstance().getEmployeeDAO().selectAllEmployees();

			String message = employeeList.size() + " row(s) retrieved successfully.";
			
			ResponseData data = new ResponseData();
			data.setEmployeeList(employeeList);
			employeeResponse.setData(data);
			employeeResponse.setDetails(generateSuccessDetails(message));
			
			logger.log(Level.INFO, "EmployeeServiceImpl: readAll(): {0}", employeeList.size());
		} catch (Exception e) {
			logger.log(Level.INFO, e.getMessage(), e);
			return generateFailureResponse(generateFailureDetails("128", e.getMessage()));
		}
		logger.log(Level.INFO, "EmployeeServiceImpl: readAll: ended");
		return employeeResponse;
	}

	public EmployeeResponse delete(String empNo) {
		EmployeeResponse employeeResponse = new EmployeeResponse();
		try {
			if (Validator.isEmpty(empNo)) {
				return generateFailureResponse(generateFailureDetails("1", Constants.ERROR_INVALID_INPUT));
			}
			logger.log(Level.INFO, "EmployeeServiceImpl: delete() invoked for the employee: [{0}]", empNo);
			
			int result = DAOFactory.getInstance().getEmployeeDAO().deleteEmployee(empNo);
			
			String message = result + " row(s) deleted successfully.";
			
			ResponseData data = new ResponseData();
			employeeResponse.setData(data);
			employeeResponse.setDetails(generateSuccessDetails(message));
			
			logger.log(Level.INFO, "EmployeeServiceImpl: delete(): {0}", message);
		} catch (Exception e) {
			logger.log(Level.INFO, e.getMessage(), e);
			return generateFailureResponse(generateFailureDetails("128", e.getMessage()));
		}
		logger.log(Level.INFO, "EmployeeServiceImpl: delete: ended");
		return employeeResponse;
	}

	private ResponseDetails generateFailureDetails(String problemTypes, String message) {
		ResponseDetails details = new ResponseDetails();
		details.setStatus(Constants.FAILURESTATUS);
		if (problemTypes == null) {
			details.setProblemTypes("65536");
		} else {
			details.setProblemTypes(problemTypes);
		}
		details.setReturnCode("-1");
		details.setMessage(message);
		return details;
	}
	  
	private ResponseDetails generateSuccessDetails(String message) {
		ResponseDetails details = new ResponseDetails();
		details.setStatus(Constants.SUCCESSSTATUS);
		details.setProblemTypes("0");
		details.setReturnCode("0");
		details.setMessage(message);
		return details;
	}
	  
	private EmployeeResponse generateFailureResponse(ResponseDetails details) {
		EmployeeResponse employeeResponse = new EmployeeResponse();
		ResponseData data = new ResponseData();
		
		employeeResponse.setData(data);
		employeeResponse.setDetails(details);
		
		return employeeResponse;
	}	
}
