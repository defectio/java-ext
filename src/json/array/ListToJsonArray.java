package json.array;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class ListToJsonArray {

	public static void main(String[] args) {
		
		
		Product prod1 = new Product("prod123", "Laptop", 1, 1000);
		Product prod2 = new Product("prod456", "Wireless Mouse", 2, 25);
		
		List<Product> list = new ArrayList<>();
		list.add(prod1);
		list.add(prod2);
		
		JSONArray jsonArr = new JSONArray(list);
		
		JSONObject items = new JSONObject();
		items.put("items", jsonArr);
		
		
		JSONObject obj = new JSONObject();
		obj.put("order_id", "order001");
		obj.put("date", "2025-09-16");
		obj.put("items", jsonArr);
		
		System.out.println(obj);
	}

}
