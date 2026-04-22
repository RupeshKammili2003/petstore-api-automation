package endPoints;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import payLoads.PetPoJoXML;

/*
 * This class will have the implementation of the endPoints for Pets module.
 * Each method will have the implementation of the respective CRUD operation for Pets module.
 * Capture the response and return the response to the respective test class for validation.
 * */

public class PetEndPointsXML {
	
	public static Response postPet(PetPoJoXML payload) {
		
		Response res = given()
		  .contentType("application/xml")
		  .body(payload)
		  .accept("application/xml")
		  
	    .when()
	       .post(Routes.post_Pet);
		
		return res;
		
	}
	

	public static Response getPet(long id) {
		
		Response res = given()
		 .pathParam("petid", id)
		 .accept("application/xml")
		
		.when()
		  .get(Routes.get_Pet);
		
		return res;	
	}
	

	public static Response updatePet(PetPoJoXML payload) {
		
		Response res = given()
		 .contentType("application/xml")
		 .body(payload)
		 .accept("application/xml")
		
		.when()
		  .put(Routes.put_Pet);
		
		return res;
	}
	
	public static Response deletePet(long id) {
		
		Response res = given()
		 .pathParam("petid", id)
		 .accept("application/xml")
		
		.when()
		  .delete(Routes.delete_Pet);
		
		return res;
	}

}
