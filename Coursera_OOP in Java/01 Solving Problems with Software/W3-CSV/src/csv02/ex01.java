package csv02;

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ex01 {

	public static CSVRecord hottestHourInFile(CSVParser parser) {
		
		CSVRecord largestSoFar = null;
		
		for(CSVRecord currentRow : parser) {
			
			largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
		}
		
		return largestSoFar;
	}
	
	public static void testHottest() {
		FileResource file = new FileResource("C:\\workspace\\Coursera_OOP\\Course01_W3_CSV\\nc_weather\\2015\\weather-2015-01-02.csv");
		CSVRecord largest = hottestHourInFile(file.getCSVParser());
		System.out.println("hottest temperature was " + largest.get("TemperatureF") +
				   " at " + largest.get("TimeEST"));
	}
	
	public static CSVRecord hottestInManyDays() {
		
		CSVRecord largestSoFar = null;
		
		DirectoryResource dr = new DirectoryResource();
		
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			
			CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
			
			largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
		}
		
		return largestSoFar;
	}
	
	public static CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar) {
		if(largestSoFar==null) largestSoFar = currentRow;
		
		else {
			double currTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			double largTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
		
			if(currTemp>largTemp) largestSoFar = currentRow;
		}
		return largestSoFar;
	}
	
	public static void testHottestDays() {
		CSVRecord largest = hottestInManyDays();
		System.out.println("hottest temperature was "+largest.get("TemperatureF")+
				   " at " + largest.get("DateUTC"));
	}
	
	public static void main(String[] args) {
		testHottest();
//		testHottestDays();
	}

}
