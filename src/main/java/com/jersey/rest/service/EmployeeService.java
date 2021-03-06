package com.jersey.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.jersey.rest.dto.Employee;
import com.jersey.rest.dto.EmployeeResponse;

@Path("/employee")
public class EmployeeService {
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public EmployeeResponse create(Employee request) {
		EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
		return employeeServiceImpl.create(request);
	}

	@POST
	@Path("/read")
	@Produces(MediaType.APPLICATION_JSON)
	public EmployeeResponse read(@QueryParam("empNo") String empNo) {
		EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
		return employeeServiceImpl.read(empNo);
	}

	@GET
	@Path("/readAll")
	@Produces(MediaType.APPLICATION_JSON)
	public EmployeeResponse read() {
		EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
		return employeeServiceImpl.readAll();
	}

	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public EmployeeResponse update(Employee request) {
		EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
		return employeeServiceImpl.update(request);
	}

	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public EmployeeResponse delete(String empNo) {
		EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
		return employeeServiceImpl.delete(empNo);
	}

}
