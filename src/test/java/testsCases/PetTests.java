package testsCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import endPoints.PetEndPoints;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.datafaker.Faker;
import payLoads.PetPoJo;
import utilites.CommonResponseVal;
import utilites.LogUtils;

public class PetTests {

	public Faker faker;
	public PetPoJo pet;
	
	// ThreadLocal to store id for each thread, ensuring thread-safe parallel execution
	private static final ThreadLocal<Long> petId = ThreadLocal.withInitial(() -> 0L);

	@BeforeClass
	public void dataSetUp() {
		LogUtils.info("=============== PetTests Setup Started ===============");
		faker = new Faker();
		pet = new PetPoJo();
		pet.setName(faker.name().firstName());
		pet.setPhotoUrls(new String[] { faker.internet().url() });
		LogUtils.info("Test payload generated successfully");
		LogUtils.debug("Pet Name: " + pet.getName());
		LogUtils.info("=============== PetTests Setup Completed ===============");
	}

	@Test(priority = 0)
	public void postPetTest() {

		LogUtils.info("=============== Test: POST Pet Started ===============");
		LogUtils.info("Sending POST request to create pet: " + pet.getName());
		
		Response res = PetEndPoints.postPet(pet);

		String responseBody = res.getBody().asString();
		LogUtils.debug("Response Body: " + responseBody);
		LogUtils.info("POST request completed with status code: " + res.getStatusCode());

		JsonPath path = res.jsonPath();
		CommonResponseVal.validateJsonCommonResponse(res, 200);

		petId.set(path.getLong("id"));
		LogUtils.info("Pet created successfully with ID: " + petId.get());
		res.then().log().body();
		
		LogUtils.info("=============== Test: POST Pet Completed ===============");

	}

	@Test(priority = 1, dependsOnMethods = { "postPetTest" })
	public void getPetTest() {
		LogUtils.info("=============== Test: GET Pet Started ===============");
		
		long id = petId.get();
		LogUtils.info("Fetching pet details for ID: " + id);
		Response res = PetEndPoints.getPet(id);

		LogUtils.info("GET request completed with status code: " + res.getStatusCode());
		CommonResponseVal.validateJsonCommonResponse(res, 200);

		res.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("pets.json"));
		
		res.then().log().body();
		
		JsonPath path = res.jsonPath();
		Assert.assertEquals(path.getLong("id"), id);
		//unreliable on public API, values may change between POST and GET, so skipping validation for now
		/*Assert.assertEquals(path.getString("name"), pet.getName());
		Assert.assertEquals(path.getString("photoUrls[0]"), pet.getPhotoUrls()[0]);*/
		
		LogUtils.info("Pet details retrieved and validated successfully");
		LogUtils.info("=============== Test: GET Pet Completed ===============");
	}

	@Test(priority = 2, dependsOnMethods = { "getPetTest" })
	public void updatePetTest() {
		LogUtils.info("=============== Test: UPDATE Pet Started ===============");
		
		long id = petId.get();
		LogUtils.info("Updating pet with ID: " + id);
		
		pet.setId(id);
		pet.setName(faker.name().firstName());
		pet.setPhotoUrls(new String[] { faker.internet().url() });
		
		LogUtils.debug("New Pet Name: " + pet.getName());
		
		Response res = PetEndPoints.updatePet(pet);

		LogUtils.info("PUT request completed with status code: " + res.getStatusCode());
		CommonResponseVal.validateJsonCommonResponse(res, 200);

		JsonPath path = res.jsonPath();
		Assert.assertEquals(path.getLong("id"), pet.getId());
		Assert.assertEquals(path.getString("name"), pet.getName());
		Assert.assertEquals(path.getString("photoUrls[0]"), pet.getPhotoUrls()[0]);

		LogUtils.info("Pet updated successfully");
		LogUtils.info("=============== Test: UPDATE Pet Completed ===============");
	}

	@Test(priority = 3, dependsOnMethods = { "updatePetTest" })
	public void deletePetTest() {
		LogUtils.info("=============== Test: DELETE Pet Started ===============");
		
		long id = petId.get();
		LogUtils.info("Deleting pet with ID: " + id);
		
		Response res = PetEndPoints.deletePet(id);

		LogUtils.info("DELETE request completed with status code: " + res.getStatusCode());
		CommonResponseVal.validateJsonCommonResponse(res, 200);

		JsonPath path = res.jsonPath();
		Assert.assertEquals(path.getInt("code"), 200);
		Assert.assertEquals(path.getString("message"), String.valueOf(id));
		Assert.assertEquals(path.getString("type"), "unknown");

		LogUtils.info("Pet deleted successfully");
		LogUtils.info("=============== Test: DELETE Pet Completed ===============");
	}

}
