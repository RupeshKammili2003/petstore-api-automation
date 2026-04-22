package testsCases;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import endPoints.PetEndPointsXML;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import net.datafaker.Faker;
import payLoads.PetPoJoXML;
import utilites.CommonResponseVal;
import utilites.LogUtils;

public class PetsTestsXML {

	public Faker faker;
	public PetPoJoXML pet;
	public List<String> photoUrls;
	private static final ThreadLocal<Long> ID = ThreadLocal.withInitial(() -> 0L);


	@BeforeClass
	public void dataSetUp() {
		LogUtils.info("=============== PetsTestsXML Setup Started ===============");
		faker = new Faker();
		pet = new PetPoJoXML();
		pet.setName(faker.name().firstName());
		photoUrls = new ArrayList<>();
		photoUrls.add(faker.internet().url());
		pet.setPhotoUrls(photoUrls);
		LogUtils.info("Test payload generated successfully");
		LogUtils.debug("Pet Name: " + pet.getName());
		LogUtils.debug("Photo URLs: " + photoUrls);
		LogUtils.info("=============== PetsTestsXML Setup Completed ===============");
	}

	@Test(priority = 0)
	public void postPetTest() {
		
		LogUtils.info("=============== Test: POST Pet Started ===============");
		LogUtils.info("Sending POST request to create pet: " + pet.getName());

		Response res = PetEndPointsXML.postPet(pet);

		XmlPath path = res.xmlPath();
		LogUtils.info("POST request completed with status code: " + res.getStatusCode());
		CommonResponseVal.validateXMLCommonResponse(res, 200);

		Assert.assertNotNull(path.getString("Pet.id"), "ID is null in the response");
		res.then().log().body();
		ID.set(Long.parseLong(path.getString("Pet.id")));
		LogUtils.info("Pet created successfully with ID: " + ID.get());
		LogUtils.info("=============== Test: POST Pet Completed ===============");
	}

	@Test(priority = 1, dependsOnMethods = { "postPetTest" })
	public void getPetTest() {
		
		LogUtils.info("=============== Test: GET Pet Started ===============");
		long id=ID.get();
		LogUtils.info("Fetching pet details for ID: " + id);
		
		Response res = PetEndPointsXML.getPet(id);
		LogUtils.info("GET request completed with status code: " + res.getStatusCode());
		CommonResponseVal.validateXMLCommonResponse(res, 200);

		res.then().body(RestAssuredMatchers.matchesXsdInClasspath("xmlschema.xsd"));

		XmlPath path = res.xmlPath();
		res.then().log().body();
		Assert.assertEquals(Long.parseLong(path.getString("Pet.id")), id);
		Assert.assertEquals(path.getString("Pet.name"), pet.getName());
		Assert.assertEquals(path.getString("Pet.photoUrls[0].photoUrl"), pet.getPhotoUrls().get(0));
		
		LogUtils.info("Pet details retrieved and validated successfully");
		LogUtils.info("=============== Test: GET Pet Completed ===============");
	}

	@Test(priority = 2, dependsOnMethods = { "getPetTest" })
	public void updatePetTest() {
		
		LogUtils.info("=============== Test: UPDATE Pet Started ===============");
		long id=ID.get();
		LogUtils.info("Updating pet with ID: " + id);
		
		pet.setId(id);
		pet.setName(faker.name().firstName());
		photoUrls = new ArrayList<>();
		photoUrls.add(faker.internet().url());
		pet.setPhotoUrls(photoUrls);
		
		LogUtils.debug("New Pet Name: " + pet.getName());
		LogUtils.debug("New Photo URLs: " + photoUrls);
		
		Response res = PetEndPointsXML.updatePet(pet);

		LogUtils.info("PUT request completed with status code: " + res.getStatusCode());
		CommonResponseVal.validateXMLCommonResponse(res, 200);

		XmlPath path = res.xmlPath();
		Assert.assertEquals(Long.parseLong(path.getString("Pet.id")), pet.getId());
		Assert.assertEquals(path.getString("Pet.name"), pet.getName());
		Assert.assertEquals(path.getString("Pet.photoUrls[0].photoUrl"), pet.getPhotoUrls().get(0));
		
		LogUtils.info("Pet updated successfully");
		LogUtils.info("=============== Test: UPDATE Pet Completed ===============");
	}
	
	@Test(priority = 3, dependsOnMethods = { "updatePetTest" })
	public void deletePetTest() {
		
		LogUtils.info("=============== Test: DELETE Pet Started ===============");
		long id=ID.get();
		LogUtils.info("Deleting pet with ID: " + id);
		
		Response res = PetEndPointsXML.deletePet(id);

		LogUtils.info("DELETE request completed with status code: " + res.getStatusCode());
		CommonResponseVal.validateXMLCommonResponse(res, 200);

		XmlPath path = res.xmlPath();
		Assert.assertEquals(Long.parseLong(path.getString("apiResponse.message")), id);
		Assert.assertEquals(path.getString("apiResponse.type"), "unknown");
		
		LogUtils.info("Pet deleted successfully");
		LogUtils.info("=============== Test: DELETE Pet Completed ===============");
	}


}
