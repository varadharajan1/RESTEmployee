package com.jersey.rest.service;

import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EmployeeServiceTest {

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8181;
        RestAssured.basePath = "/RESTEmployee/rest";
    }

    @Test
    public void testUserFetchesSuccess() {
    	RequestSpecification requestSPec = RestAssured.given();
    	
    	Response response = requestSPec.
        when().get("/employee/readAll").
        then().contentType(ContentType.JSON).extract().response();

    	Map<String, String> details = response.jsonPath().getMap("details");
        System.out.println(details.get("status"));
        System.out.println(details.get("returnCode"));
        System.out.println(details.get("message"));
        
    }
}
