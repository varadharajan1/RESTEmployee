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
		<title><fmt:message key="page.title.rest.employee.list" bundle="${resourceBundle}"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		
		<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
		<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
		<link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.dataTables.min.css">
	
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	    <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
		<script src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
		<script src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap.min.js"></script>
	</head>
	
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
	    
	    .bg-light-grey {
	        background: #ebedeb;
	    }
	    
	    #res-data-table .odd,
	    #res-data-table .odd:hover {
	        background: #fff;
	    }
	    
	    div#res-data-table_length {
	        width: 30%;
	        float: left;
	    }
	    
	    div#res-data-table_info {
	        width: 36%;
	        float: left;
	        padding-top: 0px;
	    }
	    #res-data-table.table td
		{
		 padding: .2rem .75rem;
		}
	    @media screen and (max-width: 767px) {
	        div#res-data-table_length,
	        div#res-data-table_info {
	            width: 100%;
	            margin: auto;
	            float: none;
	        }
	        div#res-data-table_length {
	            padding-top: .5rem;
	        }
	    }
	</style>
	
	<script>
		var dataSet = JSON.stringify(<%=request.getAttribute("dataSet")%>);
		var dataObject = JSON.parse(dataSet);
		$(document).ready(function() {
		    $('#res-data-table').DataTable( {
		    	"responsive": true,
		        "searching": false,
		        "data": dataObject,
		        "columns": [
		            { "data": "empNo", "visible":true },
		            { "data": "name", "visible":true  },
		            { "data": "email", "visible":true  },
		            { "data": "designation", "visible":true  }
		        ],
		        "order": [[1, 'asc']],
		    } );
		} );
	</script>
	
	<body>
	    <div class="container-fluid">
	        <div class="row bg-red p-2">
	            <div class="col-sm-3 text-center text-sm-left"><img src="images/rest-logo.jpg"></div>
	            <div class="col-sm-5 py-3 py-sm-2 text-lg-left text-center">
	                <div class="text-white">
	                    <h4 class="mb-0"><fmt:message key="page.heading.rest.employee.list" bundle="${resourceBundle}"/></h4>
	                </div>
	            </div>
	        </div>
	        <div class="row py-3">
	            <div class="col-12">
                    <table id="res-data-table" class="table table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th><fmt:message key="label.rest.employee.number" bundle="${resourceBundle}"/></th>
                                <th><fmt:message key="label.rest.employee.name" bundle="${resourceBundle}"/></th>
                                <th><fmt:message key="label.rest.employee.email" bundle="${resourceBundle}"/></th>
                                <th><fmt:message key="label.rest.employee.designation" bundle="${resourceBundle}"/></th>
                            </tr>
                        </thead>
                   </table> 
	            </div>
	        </div>
	    	<form id="employee-form" action="/RESTEmployee/employee" method="GET">
		        <div class="row">
		            <div class="col-12 col-sm-12">
						<input type="button" class="btn btn-primary" id="addNewEmployee" name="addNewEmployee" value="Add New Employee">		                    
		            </div>
		        </div>
		    </form>
	    </div>
	</body>
</html>
