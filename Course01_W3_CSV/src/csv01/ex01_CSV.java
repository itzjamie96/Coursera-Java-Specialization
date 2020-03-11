package csv01;

import edu.duke.*;
import org.apache.commons.csv.*;

public class ex01_CSV {

	public static void readFood() {
		FileResource file = new FileResource("C:\\workspace\\Coursera_OOP\\Course01_W3_CSV\\foods.csv");
		CSVParser parser = file.getCSVParser();
		
		for(CSVRecord record : parser) {
			System.out.print(record.get("Name")+" ");
			System.out.print(record.get("Favorite Color")+" ");
			System.out.println(record.get("Favorite Food"));
		}
	}
	
	public static void main(String[] args) {
		readFood();

	}

}
