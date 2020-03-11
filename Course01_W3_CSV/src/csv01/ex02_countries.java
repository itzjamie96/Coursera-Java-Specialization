package csv01;

import edu.duke.*;
import org.apache.commons.csv.*;

public class ex02_countries {

	public static void listExporters(CSVParser parser, String exportItem) {
		//for each row in the CSV file
		for(CSVRecord record : parser) {
			//look at the "Exports" column
			String export = record.get("Exports");
			
			//check if it contains exportItem
			if(export.contains(exportItem)) {
				//if yes, write down the "Country" from the row
				String country = record.get("Country");
				System.out.println(country);			
			}
		}
	}
	
	public static void whoExportsCoffee() {
		
		FileResource file = new FileResource("C:\\workspace\\Coursera_OOP\\Course01_W3_CSV\\exports\\exports_small_Ms.csv");
		CSVParser parser = file.getCSVParser();
		listExporters(parser, "coffee");
		
	}
	
	public static void main(String[] args) {
		whoExportsCoffee();

	}

}
