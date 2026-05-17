package json.array;

import org.json.JSONArray;
import org.json.JSONObject;

import json.basic.User;

public class JsonArrayExample {

	/**
	 * 	{
	 * 		"user_id": "12345",
	 * 		"username": "johndoe",
	 * 		"email": "johndoe@example.com",
	 * 		"address": {
	 * 			"postal_code": "62701",
	 * 			"country": "USA"
	 * 		},
	 * 		"orders" : {
	 * 			"order_id": "order001",
	 * 			"date": "2025-09-16",
	 * 			"items": [
	 * 				{
	 * 					"product_id": "prod123",
	 * 					"productName: "Laptop",
	 * 					"quantity": 1,
	 * 					"price": 1000
	 * 				},
	 * 				{
	 * 					"product_id": "prod456",
	 * 					"productName: "Wireless Mouse",
	 * 					"quantity": 2,
	 * 					"price": 25
	 * 				}
	 * 			]
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
		
		////////////////////////////////////////////////////////////////////////////////
		
		Product prod1 = new Product("prod123", "Laptop", 1, 1000);
		Product prod2 = new Product("prod456", "Wireless Mouse", 2, 25);
		
		JSONArray arr = new JSONArray();
		arr.put(new JSONObject(prod1));
		arr.put(new JSONObject(prod2));
		
		JSONObject orderJson = new JSONObject();
		orderJson.put("order_id", "order001");
		orderJson.put("date", "2025-09-16");
		orderJson.put("items", arr);
		
		obj.put("orders", orderJson);
		
		System.out.println(obj);
		// JSON Validator 검증
	}

}
