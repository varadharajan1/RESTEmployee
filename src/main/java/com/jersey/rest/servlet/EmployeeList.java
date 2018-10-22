package com.jersey.rest.servlet;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jersey.rest.dao.DAOFactory;
import com.jersey.rest.dto.Employee;

@WebServlet("/employees")
public class EmployeeList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(EmployeeList.class.getName());
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.log(Level.INFO, "EmployeeList: doGet() entered " );
		response.setContentType("text/html");

    	List<Employee> employeeList = DAOFactory.getInstance().getEmployeeDAO().selectAllEmployees();
		Gson gson = new Gson();
        Type type = new TypeToken<List<Employee>>() {}.getType();
        String json = gson.toJson(employeeList, type);

		request.setAttribute("dataSet", json);

		logger.log(Level.INFO, "EmployeeList: doGet() employeeList.size: {0}", employeeList.size() );

		RequestDispatcher dispatcher = null;
		logger.log(Level.INFO, "EmployeeList: doGet() before redirect to employee-list.jsp" );
		dispatcher = request.getRequestDispatcher("employee-list.jsp");

		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
