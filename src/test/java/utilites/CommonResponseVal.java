package utilites;

import org.testng.Assert;

import io.restassured.response.Response;

public class CommonResponseVal {
	
	
	public static void validateJsonCommonResponse(Response res, int code) {
		Assert.assertEquals(res.getStatusCode(), code, "Request failed with status code: " + res.getStatusCode());
		Assert.assertTrue(res.getTime() < 5000, "Response time should be less than 5000ms, actual: " + res.getTime() + "ms");
		Assert.assertTrue(res.getBody().asString().length() > 0, "Response body should not be empty");
		Assert.assertTrue(res.getBody().asString().length() < 51200, "Response body size should be below 50KB, actual: " + res.getBody().asString().length() + " bytes");
		
		// Case-insensitive header validation
		String contentType = res.getHeader("Content-Type");
		Assert.assertNotNull(contentType, "Content-Type header should not be null");
		Assert.assertTrue(contentType.toLowerCase().contains("application/json"), "Content-Type should contain 'application/json', actual: " + contentType);
		
		// Safe server header check
		String serverHeader = res.getHeader("server");
		if (serverHeader != null && !serverHeader.isEmpty()) {
			// Server header exists and is not empty
			Assert.assertFalse(serverHeader.isEmpty(), "Server header should not be empty");
		}
	}
	
	public static void validateXMLCommonResponse(Response res, int code) {
		Assert.assertEquals(res.getStatusCode(), code, "Request failed with status code: " + res.getStatusCode());
		Assert.assertTrue(res.getTime() < 5000, "Response time should be less than 5000ms, actual: " + res.getTime() + "ms");
		Assert.assertTrue(res.getBody().asString().length() > 0, "Response body should not be empty");
		Assert.assertTrue(res.getBody().asString().length() < 51200, "Response body size should be below 50KB, actual: " + res.getBody().asString().length() + " bytes");
		
		// Case-insensitive header validation
		String contentType = res.getHeader("Content-Type");
		Assert.assertNotNull(contentType, "Content-Type header should not be null");
		Assert.assertTrue(contentType.toLowerCase().contains("application/xml"), "Content-Type should contain 'application/xml', actual: " + contentType);
		
		// Safe server header check
		String serverHeader = res.getHeader("server");
		if (serverHeader != null && !serverHeader.isEmpty()) {
			// Server header exists and is not empty
			Assert.assertFalse(serverHeader.isEmpty(), "Server header should not be empty");
		}
	}
}
