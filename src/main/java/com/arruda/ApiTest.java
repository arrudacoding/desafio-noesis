package com.arruda;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class ApiTest {

@Test

	public void sampleLogin() {
		given().contentType("application/x-www-form-urlencoded; charset")
		.formParam("grant_type", "password")
		.formParam("username", "Test User")
		.formParam("password", "Test123&&")
		.when().post("http://demo6116845.mockable.io/login")
		.then().statusCode(200);
		}
	
}