package basics;

public class SimpleLocation {
	
	//public: anyone can access from anywhere -> for world use
	//private: only access within the class -> helpers
	
	//member variables: data the objects need to store
	//-> always private
	public double latitude;
	public double longitude;
	
	//Getters & Setters
	//Getter: take a private value and expose it outside of the class
	//Setter: takes a value and change to new value
	
	//Why not public member variables in the first place?
	//-> Getters & Setters give more control of the class

	public double getLatitude() {
		//private 한 latitude를 access할 수 있게 해줌
		return latitude;
	}	
	public void setLatitude(double latitude) {
		//여기있던 latitude를 새로 incoming하는 값으로 바꿔줌
		//아무 latitude로 못바꾸게 setter에 조건을 달아주는 방향이 있음
		if(latitude < -180 || latitude > 180) {
			System.out.println("Illegal value for latitude");
		} else {
			this.latitude = latitude;
		}
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	//default constructor
	public SimpleLocation() {
		this.latitude = 32.9;
		this.longitude = -117.2;
	}
	//constructor: method to create a new object
	public SimpleLocation (double lat, double lon) {
		//this: the calling object
		this.latitude = lat;
		this.longitude = lon;
	}
	//overloading: two different methods that do different things
	
	//CAUTION:
	//Return type is NOT part of the method signature
	//Overloaded methods CAN have different return types
	//BUT changing the return type is NOT ENOUGH for overloading
	//complier 때문에 - javac가 컴파일 순서를 파라미터 기준으로 본다고함? 

	//method: what this class can do = feature
	public double distance(SimpleLocation other) {
		//body omitted
		return 0;
	}
	
	public double distance(double otherLat, double otherLon) {
		//body omitted
		return 0;
	}
	
}
