package com.qa.rest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class getWeatherDetailsTests {
	
	
	@Test
	public void getWeatherDetails() {
		
		
		//define the base url
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		
		//define the http request
		RequestSpecification httpRequest =  RestAssured.given();
		
		//execute the request
		Response response = httpRequest.get("/Pune");
		
		//get the response
		System.out.println(response.getBody().asString());
		
		//get the status code and assert
		System.out.println(response.getStatusCode());
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		System.out.println("status line:"+ response.getStatusLine());
		 
		Headers header = response.getHeaders();
		System.out.println(header);
		
		System.out.println("ContentYpe: " + response.getHeader("Content-Type"));
		
		JsonPath jsonPath = response.jsonPath();
		
		System.out.println("City :" + jsonPath.get("City"));
		
		
		
	}
	
	

}
