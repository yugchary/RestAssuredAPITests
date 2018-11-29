package com.qa.rest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.qa.rest.objects.CustomerResponseFailure;
import com.qa.rest.objects.CustomerResponseSuccess;



public class PostCallWithJavaObjects {
//Types of authentication:
	//1. user name & password
	//2. Auth Token
	//3. Secret Keys/Token/Sessions ID/Customer ID/Consumer Key
	//4. Security based questions
	
	
	
	
	@Test
	public void createCustomerTest() {
		// 1. define the base url:
		RestAssured.baseURI = "http://restapi.demoqa.com/customer";

		// 2. define the http request:
		RequestSpecification httpRequest = RestAssured.given();

		// 3. create a json object with all the fields:
		
		org.json.simple.JSONObject requestJson = new org.json.simple.JSONObject();
		
	
		
		
		requestJson.put("FirstName", "yug1");
		requestJson.put("LastName", "akk1");
		requestJson.put("UserName", "yug1234");
		requestJson.put("Password", "pas1234");
		requestJson.put("Email", "yug1@gamil.com");

		// 4. add header:
		httpRequest.header("Content-Type", "application/json");

		// 5. add the json payload to the body of the request:
		httpRequest.body(requestJson.toJSONString());

		// 6. post the request and get the response:
		Response response = httpRequest.post("/register");

		// 7. get the response body:
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is: " + responseBody);
		System.out.println("Response code is: " + response.statusCode());


		// Deserialization the response into CustomerResponse class:
		if (response.statusCode() == 201) {
			CustomerResponseSuccess customerResponse = response.as(CustomerResponseSuccess.class);

			System.out.println(customerResponse.SuccessCode);
			System.out.println(customerResponse.Message);

			Assert.assertEquals("OPERATION_SUCCESS", customerResponse.SuccessCode);
			Assert.assertEquals("Operation completed successfully", customerResponse.Message);
		} 
		else if (response.statusCode() == 200) {
			CustomerResponseFailure customerResponse = response.as(CustomerResponseFailure.class);

			System.out.println(customerResponse.FaultId);
			System.out.println(customerResponse.fault);

			Assert.assertEquals("User already exists", customerResponse.FaultId);
			Assert.assertEquals("FAULT_USER_ALREADY_EXISTS", customerResponse.fault);
		}

	}

}
