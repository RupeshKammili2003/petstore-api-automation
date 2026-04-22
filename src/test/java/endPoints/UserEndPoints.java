package endPoints;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import payLoads.UserPojo;
import utilites.LogUtils;

public class UserEndPoints {

	public static Response post_User(UserPojo payload) {
		
		LogUtils.debug("Executing POST request to: " + Routes.post_User);
		LogUtils.debug("Request payload - Username: " + payload.getUsername() + ", Email: " + payload.getEmail());
		
		Response res = given()
		  .contentType("application/json")
		  .body(payload)
		  .accept("application/json")
		  
		.when()
		  .post(Routes.post_User);
		
		LogUtils.debug("Response status code: " + res.getStatusCode());
		LogUtils.debug("Response content type: " + res.getHeader("Content-Type"));
		
		return res;
		
	}

	public static Response get_User(String username) {
		
		LogUtils.debug("Executing GET request to: " + Routes.get_User);
		LogUtils.debug("Request parameter - Username: " + username);
		
		Response res = given()
		 .accept("application/json")
		 .pathParam("username", username)
		 
	    .when()
	      .get(Routes.get_User);
		
		LogUtils.debug("Response status code: " + res.getStatusCode());
		LogUtils.debug("Response content type: " + res.getHeader("Content-Type"));
		
	  return res;
	}
	

	public static Response put_User(UserPojo payload, String username) {
		
		LogUtils.debug("Executing PUT request to: " + Routes.put_User);
		LogUtils.debug("Request parameter - Username: " + username);
		LogUtils.debug("Updated payload - Email: " + payload.getEmail());
		
		 Response res = given()
		  .contentType("application/json")
		  .body(payload)
		  .pathParam("username", username)
		  .accept("application/json")
		
		 .when()
		  .put(Routes.put_User);
		 
		LogUtils.debug("Response status code: " + res.getStatusCode());
		LogUtils.debug("Response content type: " + res.getHeader("Content-Type"));
		 
		 return res;
	}
	
	public static Response delete_User(String username) {
		
		LogUtils.debug("Executing DELETE request to: " + Routes.delete_User);
		LogUtils.debug("Request parameter - Username: " + username);
		
		 Response res = given()
		  .pathParam("username", username)
		 
	    .when()
	      .delete(Routes.delete_User);
		
		LogUtils.debug("Response status code: " + res.getStatusCode());
		 
		 return res;
	}
	
	
}
