package basics;

public class LocationTester {

	public static void main(String[] args) {
		SimpleLocation current = new SimpleLocation(32.9, -117.2);
		SimpleLocation other = new SimpleLocation(-12.0, -77.0);
		
		System.out.println(current.distance(current));
			
	}

}
