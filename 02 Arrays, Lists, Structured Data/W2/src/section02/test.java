package section02;

import java.util.HashMap;

public class test {

	public static void main(String[] args) {

		// Create a HashMap object called capitalCities
		HashMap<String, String> capitalCities = new HashMap<String, String>();

		// Add keys and values (Country, City)
		capitalCities.put("Korea", "Seoul");
		capitalCities.put("Poland", "Warsaw");
		capitalCities.put("Germany", "Berlin");
		
//		capitalCities.clear();
		
		System.out.println(capitalCities.containsKey("China"));
		System.out.println(capitalCities);
		
//		System.out.println(capitalCities.get("Korea"));;

	}

}
