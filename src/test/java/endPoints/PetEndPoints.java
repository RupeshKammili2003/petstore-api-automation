package endPoints;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import payLoads.PetPoJo;
import utilites.LogUtils;

/*
 * This class will have the implementation of the endPoints for Pets module.
 * Each method will have the implementation of the respective CRUD operation for Pets module.
 * Capture the response and return the response to the respective test class for validation.
 * */

public class PetEndPoints {
	
	public static Response postPet(PetPoJo payload) {
		
		LogUtils.debug("Executing POST request to: " + Routes.post_Pet);
		LogUtils.debug("Request payload - Pet Name: " + payload.getName());
		
		Response res = given()
		  .contentType("application/json")
		  .body(payload)
		  .accept("application/json")
		  
	    .when()
	       .post(Routes.post_Pet);
		
		LogUtils.debug("Response status code: " + res.getStatusCode());
		LogUtils.debug("Response content type: " + res.getHeader("Content-Type"));
		
		return res;
		
	}
	
	public static Response getPet(long id) {
		
		LogUtils.debug("Executing GET request to: " + Routes.get_Pet);
		LogUtils.debug("Request parameter - Pet ID: " + id);
		
		Response res = given()
		 .pathParam("petid", id)
		 .accept("application/json")
		
		.when()
		  .get(Routes.get_Pet);
		
		LogUtils.debug("Response status code: " + res.getStatusCode());
		LogUtils.debug("Response content type: " + res.getHeader("Content-Type"));
		
		return res;	
	}
	
	public static Response updatePet(PetPoJo payload) {
		
		LogUtils.debug("Executing PUT request to: " + Routes.put_Pet);
		LogUtils.debug("Request payload - Pet ID: " + payload.getId() + ", Pet Name: " + payload.getName());
		
		Response res = given()
		 .contentType("application/json")
		 .body(payload)
		 .accept("application/json")
		
		.when()
		  .put(Routes.put_Pet);
		
		LogUtils.debug("Response status code: " + res.getStatusCode());
		LogUtils.debug("Response content type: " + res.getHeader("Content-Type"));
		
		return res;
	}
	
	public static Response deletePet(long id) {
		
		LogUtils.debug("Executing DELETE request to: " + Routes.delete_Pet);
		LogUtils.debug("Request parameter - Pet ID: " + id);
		
		Response res = given()
		 .pathParam("petid", id)
		
		.when()
		  .delete(Routes.delete_Pet);
		
		LogUtils.debug("Response status code: " + res.getStatusCode());
		
		return res;
	}

}
