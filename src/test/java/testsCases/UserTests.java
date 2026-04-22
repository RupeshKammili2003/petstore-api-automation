package testsCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import endPoints.UserEndPoints;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.datafaker.Faker;
import payLoads.UserPojo;
import utilites.CommonResponseVal;
import utilites.LogUtils;

public class UserTests {
	
	public Faker faker;
	public UserPojo payload;
	long id;
	
	@BeforeClass
	public void setup() {
		
		LogUtils.info("=============== UserTests Setup Started ===============");
		faker = new Faker();
		payload = new UserPojo();
		
		payload.setUsername(faker.name().username());
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().emailAddress());
		payload.setPassword(faker.internet().password(5, 10));
		payload.setPhone(faker.phoneNumber().cellPhone());
		
		LogUtils.info("Test payload generated successfully");
		LogUtils.debug("Username: " + payload.getUsername());
		LogUtils.debug("Email: " + payload.getEmail());
		LogUtils.info("=============== UserTests Setup Completed ===============");
	}
	
	@Test(priority = 1)
	public void testPostUser() {
		
		LogUtils.info("=============== Test: POST User Started ===============");
		LogUtils.info("Sending POST request to create user: " + payload.getUsername());
		
		Response res = UserEndPoints.post_User(payload);
		
		LogUtils.info("POST request completed with status code: " + res.getStatusCode());
		CommonResponseVal.validateJsonCommonResponse(res, 200);
		
		JsonPath js = res.jsonPath();
		Assert.assertEquals(Integer.parseInt(js.getString("code")), 200);
		Assert.assertEquals(js.getString("type"), "unknown");
		
		String messge = js.getString("message");
		id = Long.parseLong(messge);
		LogUtils.info("User created successfully with ID: " + id);
		LogUtils.info("=============== Test: POST User Completed ===============");
		
	}
	
	@Test(priority = 2, dependsOnMethods = "testPostUser")
	public void testGetUser() {
		
		LogUtils.info("=============== Test: GET User Started ===============");
		LogUtils.info("Fetching user details for username: " + payload.getUsername());
		 
		Response res = UserEndPoints.get_User(payload.getUsername());
		res.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("User.json"));
		
		LogUtils.info("GET request completed with status code: " + res.getStatusCode());
		CommonResponseVal.validateJsonCommonResponse(res, 200);
		
		JsonPath js = res.jsonPath();
		Assert.assertEquals(js.getString("username"), payload.getUsername());
		Assert.assertEquals(js.getString("firstName"), payload.getFirstName());
		Assert.assertEquals(js.getString("lastName"), payload.getLastName());
		Assert.assertEquals(js.getString("email"), payload.getEmail());
		Assert.assertEquals(js.getString("password"), payload.getPassword());
		Assert.assertEquals(js.getString("phone"), payload.getPhone());
		Assert.assertEquals(js.getLong("id"), id);
		Assert.assertEquals(js.getInt("userStatus"), 0);
		
		LogUtils.info("User details retrieved and validated successfully");
		LogUtils.info("=============== Test: GET User Completed ===============");
		
	}
	
	@Test(priority = 3, dependsOnMethods = "testGetUser")
	public void testUpdateUser() {
 
		LogUtils.info("=============== Test: UPDATE User Started ===============");
		LogUtils.info("Updating user with username: " + payload.getUsername());
		
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().emailAddress());
		payload.setPassword(faker.internet().password(5, 10));
		payload.setPhone(faker.phoneNumber().cellPhone());
		
		LogUtils.debug("New Username: " + payload.getUsername());
		LogUtils.debug("New Email: " + payload.getEmail());
		
		Response res = UserEndPoints.put_User(payload, payload.getUsername());
		
		LogUtils.info("PUT request completed with status code: " + res.getStatusCode());
		CommonResponseVal.validateJsonCommonResponse(res, 200);
		
		JsonPath js = res.jsonPath();
		Assert.assertEquals(Integer.parseInt(js.getString("code")), 200);
		Assert.assertEquals(js.getString("type"), "unknown");
		
		LogUtils.info("User updated successfully");
		LogUtils.info("=============== Test: UPDATE User Completed ===============");
	}
	
	@Test(priority = 4, dependsOnMethods = "testUpdateUser")
	public void testDeleteUser() {
		
		LogUtils.info("=============== Test: DELETE User Started ===============");
		LogUtils.info("Deleting user with username: " + payload.getUsername());
		
		Response res = UserEndPoints.delete_User(payload.getUsername());
		
		LogUtils.info("DELETE request completed with status code: " + res.getStatusCode());
		CommonResponseVal.validateJsonCommonResponse(res, 200);

		JsonPath js = res.jsonPath();
		Assert.assertEquals(Integer.parseInt(js.getString("code")), 200);
		Assert.assertEquals(js.getString("type"), "unknown");
		Assert.assertEquals(js.getString("message"), payload.getUsername());
		
		LogUtils.info("User deleted successfully");
		LogUtils.info("=============== Test: DELETE User Completed ===============");
	}
	

}
