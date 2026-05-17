package json.basic;

import java.util.HashMap;

import org.json.JSONObject;

public class BasicJsonExample {

	/**
	 * 	{
	 * 		"user_id": "12345",
	 * 		"username": "johndoe"
	 * 	}		
	 */
	public static void main(String[] args) {
		
		
		// 1. String to JSON
		String jsonStr = "{\"user_id\": \"12345\", \"userName\":\"johndoe\"}";
		JSONObject obj = new JSONObject(jsonStr);		
		obj.put("email", "johndoe@example.com");   // add data
		
		System.out.println("jsonStr : " + obj); //obj.toString() 과 동일
		
		// 2. Map to JSON
		HashMap<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("user_id", "12345");
		jsonMap.put("userName", "johndoe");
		
		JSONObject obj2 = new JSONObject(jsonMap);
		System.out.println("jsonMap : " + obj2);
		
		// 3. Bean to JSON
		User user = new User("12345", "johndoe");
		
		JSONObject obj3 = new JSONObject(user);
		System.out.println("jsonObj : " + obj3);
		

	}

}
