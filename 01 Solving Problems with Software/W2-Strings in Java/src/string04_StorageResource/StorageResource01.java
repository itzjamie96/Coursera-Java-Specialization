package string04_StorageResource;

import edu.duke.*;

public class StorageResource01 {

	public static void main(String[] args) {
		//StorageResource: holds a collection of Strings
		StorageResource sr = new StorageResource();
		
		sr.add("Hello");
		sr.add("World");
		
		for (String s : sr.data()) 
			System.out.println(s);

	}

}
