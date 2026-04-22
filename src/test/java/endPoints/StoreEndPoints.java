package endPoints;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import payLoads.StorePojo;
import utilites.LogUtils;

public class StoreEndPoints {
	
	public static Response post_Store(StorePojo payload) {
		
		LogUtils.debug("Executing POST request to: " + Routes.post_Store);
		LogUtils.debug("Request payload - Pet ID: " + payload.getPetId() + ", Quantity: " + payload.getQuantity());
		
		Response res = given()
		  .contentType("application/json")
		  .body(payload)
		  .accept("application/json")
		  
		.when()
		  .post(Routes.post_Store);
		
		LogUtils.debug("Response status code: " + res.getStatusCode());
		LogUtils.debug("Response content type: " + res.getHeader("Content-Type"));
		
		return res;
	}
	
	public static Response get_Store(long orderID) {
		
		LogUtils.debug("Executing GET request to: " + Routes.get_Store);
		LogUtils.debug("Request parameter - Order ID: " + orderID);
		
		Response res = given()
		 .pathParam("orderid", orderID)
		 .accept("application/json")
		
		.when()
		  .get(Routes.get_Store);
		
		LogUtils.debug("Response status code: " + res.getStatusCode());
		LogUtils.debug("Response content type: " + res.getHeader("Content-Type"));
		
		return res;
	}

	public static Response delete_Store(long orderID) {
		
		LogUtils.debug("Executing DELETE request to: " + Routes.delete_Store);
		LogUtils.debug("Request parameter - Order ID: " + orderID);
		 
		Response res = given()
		 .pathParam("orderid", orderID)
		 
	    .when()
	      .delete(Routes.delete_Store);
		
		LogUtils.debug("Response status code: " + res.getStatusCode());
		
		return res;
	}
}
