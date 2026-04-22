package testsCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import endPoints.StoreEndPoints;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.datafaker.Faker;
import payLoads.StorePojo;
import utilites.CommonResponseVal;
import utilites.LogUtils;

public class StoreTests {

	public Faker faker;
	public StorePojo pojo;
	private static final ThreadLocal<Long> orderID = ThreadLocal.withInitial(() -> 0L);

	@BeforeClass
	public void beforeClass() {

		LogUtils.info("=============== StoreTests Setup Started ===============");
		faker = new Faker();
		pojo = new StorePojo();

		pojo.setPetId(faker.number().numberBetween(1, 20));
		pojo.setQuantity(faker.number().numberBetween(1, 20));
		
		LogUtils.info("Test payload generated successfully");
		LogUtils.debug("Pet ID: " + pojo.getPetId());
		LogUtils.debug("Quantity: " + pojo.getQuantity());
		LogUtils.info("=============== StoreTests Setup Completed ===============");
	}

	@Test(priority = 0)
	public void post_Store() {

		LogUtils.info("=============== Test: POST Store Order Started ===============");
		LogUtils.info("Creating store order for Pet ID: " + pojo.getPetId() + ", Quantity: " + pojo.getQuantity());
		
		Response res = StoreEndPoints.post_Store(pojo);

		LogUtils.info("POST request completed with status code: " + res.getStatusCode());
		CommonResponseVal.validateJsonCommonResponse(res, 200);

		JsonPath js = res.jsonPath();
		Assert.assertNotNull(js.get("id"));
		orderID.set(js.get("id"));

		LogUtils.info("Store order created successfully with Order ID: " + orderID.get());
		
		LogUtils.info("=============== Test: POST Store Order Completed ===============");
	}

	@Test(priority = 1, dependsOnMethods = "post_Store")
	public void get_Store() {
		long id = orderID.get();
		LogUtils.info("=============== Test: GET Store Order Started ===============");
		LogUtils.info("Fetching store order details for Order ID: " + id);
		
		Response res = StoreEndPoints.get_Store(id);
		res.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Store.json"));

		LogUtils.info("GET request completed with status code: " + res.getStatusCode());
		CommonResponseVal.validateJsonCommonResponse(res, 200);

		JsonPath js = res.jsonPath();
		Assert.assertEquals(js.getLong("id"), id);
		Assert.assertEquals(js.getInt("petId"), pojo.getPetId());
		Assert.assertEquals(js.getInt("quantity"), pojo.getQuantity());
		Assert.assertEquals(js.getBoolean("complete"), false);
		
		LogUtils.info("Store order details retrieved and validated successfully");
		LogUtils.info("=============== Test: GET Store Order Completed ===============");
	}

	@Test(priority = 2, dependsOnMethods = "get_Store")
	public void delete_Store() {
		long id = orderID.get();
		LogUtils.info("=============== Test: DELETE Store Order Started ===============");
		LogUtils.info("Deleting store order with Order ID: " + id);
		
	
		Response res = StoreEndPoints.delete_Store(id);
		CommonResponseVal.validateJsonCommonResponse(res, 200);

		JsonPath js = res.jsonPath();
		Assert.assertEquals(Integer.parseInt(js.getString("code")), 200);
		Assert.assertEquals(js.getString("type"), "unknown");
		Assert.assertEquals(js.getString("message"), String.valueOf(id));

		LogUtils.info("Store order deleted successfully");
		LogUtils.info("=============== Test: DELETE Store Order Completed ===============");
	}

}
