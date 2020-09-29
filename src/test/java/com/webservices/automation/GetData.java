package com.webservices.automation;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;


public class GetData
{
	
	/**
	 *  First Scenario Validating Ship ID
	 */
	@Test
	public void validateShipID() {
	Response resp =	given().
					when().
					get("https://api.spacexdata.com/v4/launches/latest");
	
	System.out.println(resp.asString());
	
	ArrayList shipId = resp
					.then().contentType(ContentType.JSON).extract().path("fairings.ships");
	
	System.out.println("Ship Id ======"+ shipId);
	
	
		
	ArrayList shipIdExpected = new ArrayList();
	
	
	shipIdExpected.add("5ea6ed2e080df4000697c908");
	
	
	System.out.println("shipIdExpected======"+ shipIdExpected);
	
	Assert.assertEquals(shipId, shipIdExpected);
	
	}
	
	/**
	 * Second Scenario Validating customer/order ID
	 * 
	 */
	
	@Test
	public void validateID()
	{
		Response resp =	given().
				when().
				get("https://api.spacexdata.com/v4/launches/latest");
		
		String orderId = resp
				.then().contentType(ContentType.JSON).extract().path("id");
		
		
		
		System.out.println("Order id ========" +orderId);
		
		Assert.assertEquals(orderId,"5ef6a1e90059c33cee4a828a");
	}
	
	@Test
	public void validateShipIDfromallShips()
	{
	
		Response resp =	given().
				when().
				get("https://api.spacexdata.com/v4/launches/latest");
		
		ArrayList shipId = resp
				.then().contentType(ContentType.JSON).extract().path("fairings.ships");
		
		System.out.println("Ship Id ======"+ shipId);
	
	String image = resp
			.then().contentType(ContentType.JSON).extract().path("links.patch.large");
	System.out.println("image======"+ image);
	
	String media = resp
			.then().contentType(ContentType.JSON).extract().path("links.reddit.media");
		
	System.out.println("media========="+ media);
	
	
	ArrayList allShipIds = resp.then().contentType(ContentType.JSON).extract().path("ships");
	System.out.println("al======"+ allShipIds);
	
	System.out.println("directly contains ===== " + allShipIds.containsAll(shipId));
	
	
   Assert.assertTrue(allShipIds.containsAll(shipId));
	}
	
	/**
	 * Scenario which can be validated cores 
	 */
	@Test
	public void validateCoresData()
	{
		
		Response resp =	given().
				when().
				get("https://api.spacexdata.com/v4/launches/latest");
		
	ArrayList landingType = resp
			.then().contentType(ContentType.JSON).extract().path("cores.landing_type");
	
	System.out.println("landingType====="+ landingType);
	
	ArrayList landingAttempt = resp
			.then().contentType(ContentType.JSON).extract().path("cores.landing_attempt");
	
	System.out.println("landingAttempt====="+ landingAttempt);
	
	ArrayList cores = resp.then().contentType(ContentType.JSON).extract().jsonPath().get("cores");
	
	System.out.println("cores======"+ cores);
	}
	
	

}
