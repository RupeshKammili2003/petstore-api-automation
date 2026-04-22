package endPoints;

/*
 * Swagger API endPoints for Pets, Store and User
 * Each module has its own CRUD implementation.
 * 
 * */

public class Routes {
	
	//Base URL
	public static String base_URI ="https://petstore.swagger.io/v2";
	
	//Pets JSON EndPoints
	//No need of creating separate endPoints for XML implementation as the endPoints are same for both JSON and XML, only the content type will be different in the header.
	public static String post_Pet=base_URI+"/pet";
	public static String get_Pet = base_URI+"/pet/{petid}";
	public static String put_Pet = base_URI+"/pet";
	public static String delete_Pet = base_URI+"/pet/{petid}";
	
	//Store JSON EndPoints - No put/patch endPoints for Store module as per the Swagger documentation
	public static String post_Store=base_URI+"/store/order";
	public static String get_Store=base_URI+"/store/order/{orderid}";
	public static String delete_Store=base_URI+"/store/order/{orderid}";
	
	//User JSON EndPoints
	public static String post_User=base_URI+"/user";
	public static String get_User=base_URI+"/user/{username}";
	public static String put_User=base_URI+"/user/{username}";
	public static String delete_User=base_URI+"/user/{username}";
}
