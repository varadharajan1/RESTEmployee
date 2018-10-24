<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.jersey.rest.util.Constants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="com.jersey.rest.employee.nls.resource" var="resourceBundle"/>
<c:set var="CREATE_EMP_FORM" value="<%=Constants.CREATE_EMP_FORM %>" scope="request"/>
<c:set var="UPDATE_EMP_FORM" value="<%=Constants.UPDATE_EMP_FORM %>" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<c:choose>
		    <c:when test="${empForm == CREATE_EMP_FORM}">
			<title><fmt:message key="page.title.rest.employee.create" bundle="${resourceBundle}"/></title>
		    </c:when>
		    <c:otherwise>
			<title><fmt:message key="page.title.rest.employee.update" bundle="${resourceBundle}"/></title>
		    </c:otherwise>
		</c:choose>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">	
		
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>	
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>	
	    <script>
			$("#submit").click(function() {
				$("#employee-form").submit();
			});
			$("#reset").click(function() {
				$('#employee-form').trigger("reset");
			});
			$("#employee-form").submit(function(event){
				event.preventDefault(); //prevent default action 
			    var post_url = '/RESTEmployee/rest/employee/create';
				var form_data = $(this).serialize(); //Encode form elements for submission
				
				$.post( post_url, form_data, function( response ) {
					window.location.replace('/RESTEmployee/employees');
				});
			});			
			$("#delete").click(function() {
			    var post_url = '/RESTEmployee/rest/employee/delete';
				var empNo = $("input#empNo").val();
				
				$.post( post_url, empNo, function( response ) {
					window.location.replace('/RESTEmployee/employees');
				});
			});			
	    </script>
		<style>
		    .bg-grey {
		        background: #dcdedc;
		    }
		    .bg-red {
		        background: #b6121b;
		    }
			span.input-group-addon {
			    background: #ddd;
			    padding: 7px;
			    cursor: pointer;
				border-radius: 0px 4px 4px 0px;
			}
		</style>
	</head>
	<body>
		<div class="container-fluid">
	        <div class="row bg-red p-2">
	            <div class="col-sm-3 text-center text-sm-left"><img src="images/rest-logo.jpg"></div>
	            <div class="col-sm-6 py-3 py-sm-2 text-lg-left text-center">
	                <div class="text-white">
						<c:choose>
						    <c:when test="${empForm == CREATE_EMP_FORM}">
			                   <h4 class="mb-0"><fmt:message key="page.heading.rest.employee.create" bundle="${resourceBundle}"/></h4>
						    </c:when>
						    <c:otherwise>
			                   <h4 class="mb-0"><fmt:message key="page.heading.rest.employee.update" bundle="${resourceBundle}"/></h4>
						    </c:otherwise>
						</c:choose>
	                </div>
	            </div>
	        </div>
			<form id="employee-form" name="employee-form" action="/RESTEmployee/employees" method="POST">
				<input type="hidden" id="EMP_FORM_TYPE" name="EMP_FORM_TYPE" value="${empForm}">
		        <div class="row px-lg-4 pt-lg-4 pt-3">
		            <div class="col-md-6 col-xl-5 col-12">
		                <div class="row p-sm-2">
		                    <div class="col-sm-4 pb-2">
		                        <strong><fmt:message key="label.rest.employee.number" bundle="${resourceBundle}"/></strong>
		                    </div>
		                    <div class="col-sm-8">
								<c:choose>
								    <c:when test="${empForm == CREATE_EMP_FORM}">
										<input type="text" class="form-control" id="empNo" name="empNo" value="${empData.empNo}">		                    
								    </c:when>
								    <c:otherwise>
								    	<input type="hidden" class="form-control" id="empNo" name="empNo" value="${empData.empNo}">
					                   	<h4 class="mb-0"><c:out value="${empData.empNo}"/></h4>
								    </c:otherwise>
								</c:choose>
		                    </div>
		                </div>
		                <div class="row p-sm-2">
		                    <div class="col-sm-4 pb-2">
		                        <strong><fmt:message key="label.rest.employee.name" bundle="${resourceBundle}"/></strong>
		                    </div>
		                    <div class="col-sm-8">
								<input type="text" class="form-control" id="name" name="name" value="${empData.name}">		                    
		                    </div>
		                </div>
		                <div class="row p-sm-2">
		                    <div class="col-sm-4 pb-2">
		                        <strong><fmt:message key="label.rest.employee.email" bundle="${resourceBundle}"/></strong>
		                    </div>
		                    <div class="col-sm-8">
								<input type="text" class="form-control" id="email" name="email" value="${empData.email}">		                    
		                    </div>
		                </div>
		                <div class="row p-sm-2">
		                    <div class="col-sm-4 pb-2">
		                        <strong><fmt:message key="label.rest.employee.designation" bundle="${resourceBundle}"/></strong>
		                    </div>
		                    <div class="col-sm-8">
								<input type="text" class="form-control" id="designation" name="designation" value="${empData.designation}">		                    
		                    </div>
		                </div>
		                <div class="row p-sm-2">
		                    <div class="col-sm-12 text-center">
								<input type="button" class="btn btn-secondary" id="cancel" name="cancel" value="Cancel">		                    
							    <c:if test="${empForm == UPDATE_EMP_FORM}">
								<input type="button" class="btn btn-secondary" id="delete" name="delete" value="Delete">
							    </c:if>
								<input type="button" class="btn btn-primary" id="submit" name="submit" value="Submit">		                    
		                    </div>
		                </div>
		            </div>
		        </div>
	        </form>
	    </div>
    </body>
</html>
