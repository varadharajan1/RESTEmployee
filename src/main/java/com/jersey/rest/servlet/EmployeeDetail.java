package com.jersey.rest.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jersey.rest.dao.DAOFactory;
import com.jersey.rest.dto.Employee;
import com.jersey.rest.util.Constants;
import com.jersey.rest.util.Validator;

@WebServlet("/employee")
public class EmployeeDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(EmployeeDetail.class.getName());
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
		logger.log(Level.INFO, "EmployeeDetail: doGet() entered " );
		response.setContentType("text/html");

		RequestDispatcher dispatcher = null;
		String fileName = "employee.jsp";
		try {
			String empNo = request.getParameter("empno");
		    if (Validator.isNotEmpty(empNo)) {

		    	Employee emp = DAOFactory.getInstance().getEmployeeDAO().selectEmployee(empNo);
				if(emp != null) {
			    	empNo = emp.getId();
				    if (Validator.isNotEmpty(empNo)) {
				    	request.setAttribute(Constants.EMP_FORM, Constants.UPDATE_EMP_FORM);
				    	request.setAttribute("empData", emp);
				    } else {
				    	request.setAttribute(Constants.EMP_FORM, Constants.CREATE_EMP_FORM);
				    }
			    } else {
			    	request.setAttribute(Constants.EMP_FORM, Constants.CREATE_EMP_FORM);
			    }
		    } else {
		    	request.setAttribute(Constants.EMP_FORM, Constants.CREATE_EMP_FORM);
		    }
		} catch (Exception e) {
			logger.log(Level.INFO, e.getMessage(), e);
	    	request.setAttribute("message", e.getMessage());
	    	fileName = "error.jsp";
		}
		logger.log(Level.INFO, "EmployeeDetail: doGet() before redirect to {0}", fileName );
		dispatcher = request.getRequestDispatcher(fileName);
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
