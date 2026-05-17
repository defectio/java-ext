package json.basic;

import org.json.JSONObject;

public class NestedJsonExample {


	/**
	 * 	{
	 * 		"user_id": "12345",
	 * 		"username": "johndoe",
	 * 		"email": "johndoe@example.com",
	 * 		"address": {
	 * 			"postal_code": "62701",
	 * 			"country": "USA"
	 * 		}
	 * 	}			
	 */
	public static void main(String[] args) {
		
		User user = new User("12345", "johndoe");
		
		JSONObject obj = new JSONObject(user);
		obj.put("email", "johndoe@example.com");
		
		JSONObject adrsJson = new JSONObject();
		adrsJson.put("country", "USA");
		adrsJson.put("postal_code", "62701");
		
		obj.put("address", adrsJson);
		
		System.out.println(obj);
	}

}
