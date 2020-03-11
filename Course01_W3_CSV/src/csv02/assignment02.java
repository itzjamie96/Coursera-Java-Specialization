package csv02;

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class assignment02 {
	
	public static CSVRecord coldestHourInFile(CSVParser parser) {
		
		CSVRecord coldest = null;
		
		for (CSVRecord current : parser) {
			
			if(coldest==null) coldest = current;
			
			else if (current.get("TemperatureF").contentEquals("-9999")) {
				System.out.println("-9999 found => skip");
			}
			
			else {
				double currTemp = Double.parseDouble(current.get("TemperatureF"));
				double lowTemp = Double.parseDouble(coldest.get("TemperatureF"));
			
				if(currTemp<lowTemp) coldest = current;
			}
		}
		
		return coldest;
	}
	
	public static void testColdest() {
		FileResource fr = new FileResource("C:\\workspace\\Coursera_OOP\\Course01_W3_CSV\\nc_weather\\2014\\weather-2014-05-01.csv");
		CSVParser parser = fr.getCSVParser();
		CSVRecord csv = coldestHourInFile(parser);
		System.out.println("Coldest temp was "+csv.get("TemperatureF")+" at"+csv.get("DateUTC"));
	}
	
	public static String fileWithColdestTemp() {
		
		CSVRecord coldest = null;
		String name = null;
		DirectoryResource dr = new DirectoryResource();
		
		for (File f : dr.selectedFiles()) {
			
			FileResource fr = new FileResource(f);
			CSVRecord current = coldestHourInFile(fr.getCSVParser());
			
			if(coldest==null) coldest = current;
			
			else {
				double currTemp = Double.parseDouble(current.get("TemperatureF"));
				double lowTemp = Double.parseDouble(coldest.get("TemperatureF"));
			
				if(currTemp<lowTemp) {
					coldest = current;
					name = f.getName();
				}
			}
		}
		return name;
	}
	
	
	public static void testFileWithColdestTemp() {
		String coldestFile = fileWithColdestTemp();
		System.out.println("Coldest day was in file "+coldestFile);
		
		String file = "C:\\workspace\\Coursera_OOP\\Course01_W3_CSV\\nc_weather\\2013\\"+coldestFile;
		FileResource fr = new FileResource(file);
		CSVRecord coldestTemp = coldestHourInFile(fr.getCSVParser());
		System.out.println("Coldest temperature was "+coldestTemp.get("TemperatureF"));
	
		System.out.println("All the Temperatures on the coldest day were:");
		
		for (CSVRecord record : fr.getCSVParser())
			System.out.println(record.get("DateUTC")+": "+record.get("TemperatureF"));

	}
	
	/////////////////////////////////////////////////////////////////
	
	public static CSVRecord compareHumidity(CSVRecord current, CSVRecord humid) {
		
		if(humid==null) humid = current;
		
		else if(current.get("Humidity").contentEquals("N/A")) 
			System.out.println("N/A found => Skip!");
		
		else {
			double currHumid = Double.parseDouble(current.get("Humidity"));
			double lowHumid = Double.parseDouble(humid.get("Humidity"));
		
			if(currHumid<lowHumid) humid = current;
		}
		return humid;
	}
	
	public static CSVRecord lowestHumidityInfile(CSVParser parser) {
		
		CSVRecord humid = null;
		
		for (CSVRecord current : parser) {
			
			humid = compareHumidity(current, humid);

		}
	
		return humid;
	}
	
	public static void testLowestHumidity() {
		FileResource fr = new FileResource("C:\\workspace\\Coursera_OOP\\Course01_W3_CSV\\nc_weather\\2014\\weather-2014-07-22.csv");
		CSVParser parser = fr.getCSVParser();
		CSVRecord csv = lowestHumidityInfile(parser);
		System.out.println("Lowest Humidity was "+csv.get("Humidity")+" at "+csv.get("DateUTC"));
	}
	
	public static CSVRecord lowestHumidityInManyFiles() {
		
		CSVRecord humid = null;
		DirectoryResource dr = new DirectoryResource();
		
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVRecord current = lowestHumidityInfile(fr.getCSVParser());
			
			humid = compareHumidity(current, humid);
		}
		
		return humid;
	}
	
	public static void testLowestHumidityFiles() {
		
		CSVRecord csv = lowestHumidityInManyFiles();
		System.out.println("Lowest Humidity was "+csv.get("Humidity")+" at "+csv.get("DateUTC"));
	}
	
	public static double avgTemp(CSVParser parser) {
		
		double sum = 0;
		int count = 0;
		for(CSVRecord current : parser) {
			sum += Double.parseDouble(current.get("TemperatureF"));
			count++;
		}
		
		return sum/count;
	}
	
	public static void testAvgTemp() {
		FileResource fr = new FileResource("C:\\workspace\\Coursera_OOP\\Course01_W3_CSV\\nc_weather\\2013\\weather-2013-08-10.csv");
		CSVParser parser = fr.getCSVParser();
		System.out.println("Average temp is "+avgTemp(parser));
	}
	
	public static double avgTempHH(CSVParser parser, int value) {
		
		double sum = 0;
		int count = 0;
		for(CSVRecord current : parser) {
			if(Double.parseDouble(current.get("Humidity"))>=value) {
				sum += Double.parseDouble(current.get("TemperatureF"));
				count++;				
			} 
			
		}

		return sum/count;

	}
	
	public static void testAvgTempHH() {
		FileResource fr = new FileResource("C:\\workspace\\Coursera_OOP\\Course01_W3_CSV\\nc_weather\\2013\\weather-2013-09-02.csv");
		CSVParser parser = fr.getCSVParser();
		System.out.println("Average temp with High Humidity is "+avgTempHH(parser,80));
		
		
	}
	
	public static void main(String[] args) {
//		testColdest();
		testFileWithColdestTemp();
//		testLowestHumidity();
//		testLowestHumidityFiles();
//		testAvgTemp();
//		testAvgTempHH();
	}

}
