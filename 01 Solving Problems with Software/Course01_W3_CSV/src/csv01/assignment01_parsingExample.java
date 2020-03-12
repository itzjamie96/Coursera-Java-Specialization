package csv01;

import edu.duke.*;
import org.apache.commons.csv.*;

public class assignment01_parsingExample {

	public static void tester() {
		FileResource file = new FileResource("C:\\workspace\\Coursera_OOP\\Course01_W3_CSV\\exports\\exportdata.csv");
		CSVParser parser = file.getCSVParser();
		
//		System.out.println(countryInfo(parser, "Nauru"));;
		
//		listExportersTwoProducts(parser, "cotton", "flowers");
		
//		System.out.println(numberOfExporters(parser, "cocoa"));;
		
		bigExporters(parser, "$999,999,999,999");
	}
	
	public static String countryInfo(CSVParser parser, String country) {
		
		String info = "NOT FOUND";
		
		for(CSVRecord record : parser) {
			if(record.get("Country").contentEquals(country))
				info = record.get("Country")+": "+record.get("Exports")+": "+record.get("Value (dollars)");
				//System.out.println(record.get("Country")+": ");
		}
		return info;
	}
	
	public static void listExportersTwoProducts(CSVParser parser, String export1, String export2) {
		
		for(CSVRecord record : parser) {
			if (record.get("Exports").contains(export1) && record.get("Exports").contains(export2)) {
				System.out.println(record.get("Country"));
			}
		}
	}
	
	public static int numberOfExporters(CSVParser parser, String export) {
		
		int count = 0;
		
		for(CSVRecord record : parser) {
			if(record.get("Exports").contains(export)) count++;
		}
		
		return count;
	}
	
	public static void bigExporters(CSVParser parser, String amount) {
		
		int valueLength = amount.length();
		
		for (CSVRecord record : parser) {
			if(record.get("Value (dollars)").length()>valueLength)
				System.out.println(record.get("Country")+" "+record.get("Value (dollars)"));
		}
		
	}
	
	public static void main(String[] args) {
		tester();

	}

}
