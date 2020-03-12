package babyNames;

import edu.duke.*;

import java.io.File;
import org.apache.commons.csv.*;

public class miniProject {

	//STATIC FILE RESOURCE NAMES
	final static String test = "C:\\workspace\\Coursera_OOP\\Course01_W4_BabyNamesProject\\data\\us_babynames\\us_babynames_test\\";
	final static String fileName = "example-small.csv";
	final static String exampleSmall = test+fileName;
	final static String yearly = "C:\\workspace\\Coursera_OOP\\Course01_W4_BabyNamesProject\\data\\us_babynames\\us_babynames_by_year\\";
	
	//PRINT NAMES
	public static void printNames() {
		FileResource file = new FileResource(test+fileName);
		
		//file.getCSVParser(false) = no header
		for(CSVRecord record : file.getCSVParser(false)) {
			
			//record.get(0) : 배열 인덱스처럼
			System.out.println("Name: "+ record.get(0) 
							+ " / Gender: " + record.get(1) 
							+ " / Num Born: "+record.get(2));
		}
	}
	
	//PRINT NAMES WITH CERTAIN VALUES
	public static void printNamesWvalues() {
		FileResource file = new FileResource(exampleSmall);
		
		//file.getCSVParser(false) = no header
		for(CSVRecord record : file.getCSVParser(false)) {
			
			int numBorn = Integer.parseInt(record.get(2));
			if(numBorn <= 100) {
				
				//record.get(0) : 배열 인덱스처럼
				System.out.println("Name: "+ record.get(0) 
				+ " / Gender: " + record.get(1) 
				+ " / Num Born: "+record.get(2));
			}
			
		}
	}
	
	//TOTAL BIRTH
	public static void totalBirth(FileResource file) {
		
		int totalB = 0;
		
		int boys = 0;
		int boysCount = 0;
		
		int girls = 0;
		int girlsCount = 0;
		
		for (CSVRecord record : file.getCSVParser(false)) {
			
			int numBorn = Integer.parseInt(record.get(2));

			//int totalB += numBorn;   =>> 안됨
			//선언과 더하기를 동시에 할 수 없기 때문에!
			totalB += numBorn;
			
			if(record.get(1).equals("M")) {
				boys += numBorn;
				boysCount++;
			} else {
				girls += numBorn;
				girlsCount++;
			}
		}
		System.out.println("total briths: "+totalB);
		System.out.println("total girls: "+girls + " / total girls' names: "+girlsCount);
		System.out.println("total boys: "+boys+ " / total boys' names: "+boysCount);
		
	}
	
	//GET RANK
	public static int getRank(int year, String name, String gender, String resource) {

		//해당 년도가 맞는지 확인
		if(resource.contains(String.valueOf(year))) {
			
			FileResource file = new FileResource(resource);

			int gCount = 0;
			int bCount = 0;

			//각 성별의 수 count
			for (CSVRecord record : file.getCSVParser(false)) {
				
				if(record.get(1).contentEquals("F")) gCount++;
				else bCount++;
			
			}
			//System.out.println("gCount: "+gCount);
			//System.out.println("bCount: "+bCount);
			
			int count = 0;
			int gRank = 0;		
			int bRank = 0;
			 
			//Rank 구하기
			for(CSVRecord record : file.getCSVParser(false)) {
				
				String babyName = record.get(0);
				String babyGender = record.get(1);
				int babyNumber = Integer.parseInt(record.get(2));
				
				if(gender.contentEquals("F") && gender.contentEquals(babyGender)) {
					gRank++;
					
					if(name.contentEquals(babyName)) {
						count = gRank;
						return count;
					} 	
				}
				else if(gender.contentEquals("M") && gender.contentEquals(babyGender)) {
					bRank++;
					
					if(name.contentEquals(babyName)) {
						count = bRank;
						return count;
					}
				}
				
			}
		}
		return -1;

	}
	
	public static String getName(int year, int rank, String gender, String resource) {
		
		//해당 년도가 맞는지 확인
		if(resource.contains(String.valueOf(year))) {
			
			FileResource file = new FileResource(resource);		
			
			for (CSVRecord record : file.getCSVParser(false)) {
				int eachRank = getRank(year, record.get(0), gender,resource);
				
				if(eachRank == rank) return record.get(0);				
			}
		
		}
		
		return "NO NAME";
	}
	
	//그 해의 인기 순위로 이름찾기
	public static void whatNameInYear(String name, int year, int newYear, String gender) {
		
		//String originYear = test+"yob"+String.valueOf(year)+"short.csv";
		String originYear = yearly+"yob"+String.valueOf(year)+".csv";
		//String newNameYear = test+"yob"+String.valueOf(newYear)+"short.csv";
		String newNameYear = yearly+"yob"+String.valueOf(newYear)+".csv";
		
		//rank of origin 
		int rank = getRank(year, name, gender, originYear);
		
		//name in the newYear with rank
		String newName = getName(newYear, rank, gender, newNameYear);
		
		System.out.println(name+" born in "+year+" would be "+newName+" in "+newYear);
	}
	
	//HIGHEST RANK FOR NAMES
	public static int yearOfHighestRank(String name, String gender) {
		
		DirectoryResource dr = new DirectoryResource();
		
		int rank=0;
		int year = -1;
		
		for(File f : dr.selectedFiles()) {
			
			FileResource fr = new FileResource(f);
		
			String resource =yearly+f.getName();
			
			int currYear = Integer.parseInt(f.getName().substring(3, 7));
			
			int tmp = getRank(currYear, name, gender, resource);
			
			if(tmp != -1) {
				if(rank==0) {
					rank = tmp;
					year = currYear;
				} else {
					if(rank>tmp) {
						rank = tmp;
						year = currYear;
					}
				}
			}
		}

		return year;
	}
	
	//AVERAGE RANK
	public static double getAverageRank(String name, String gender) {
		double avg = -1.0;
		
		DirectoryResource dr = new DirectoryResource();
		
		double rank=0;
		double count=0;
		
		for(File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			
			String resource =yearly+f.getName();
			
			int currYear = Integer.parseInt(f.getName().substring(3, 7));
			
			rank += getRank(currYear, name, gender, resource);
			count++;
		}
		avg = rank/count;
		
		return avg;
	}
	
	//TOTAL BIRTH RANK
	////////////////////////////check
	public static int getTotalBrithsRankedHigher(int year, String name, String gender) {
		
		String resource = yearly+"yob1990.csv";
		int rank = getRank(year, name, gender, resource);
		System.out.println(rank);
		int sum = 0;
		
		FileResource file = new FileResource(resource);		
					
		for (CSVRecord record : file.getCSVParser(false)) {
			int eachRank = getRank(year, record.get(0), gender,resource);
				
			if(eachRank!=-1 && eachRank < rank) {
				sum += Integer.parseInt(record.get(2));
			}
		}					


		return sum;
	}
	//TEST BIRTH SUM
	public static void testBirthSum() {
		int sum = getTotalBrithsRankedHigher(1990, "Emily", "F");
		System.out.println(sum);
	}
	
	
	//TEST AVG
	public static void testAvgRank() {
		double avg = getAverageRank("Robert", "M");
		System.out.println(avg);
	}
	
	//TEST HIGH RANK YEAR
	public static void testHighYear() {
		int year = yearOfHighestRank("Mich", "M");
		System.out.println(year);
	}
	
	
	//TEST NAMEYEAR
	public static void testNameYear() {
		whatNameInYear("Owen", 1974, 2014, "M");
	}
	
	//TEST GET NAME
	public static void testGetName() {
		String resource = yearly + "yob1982.csv";
		String  name = getName(1982, 450, "M",resource);
		System.out.println(name);
	}	
	
	//TEST RANK
	public static void testRank() {
		String resource = yearly + "yob1971.csv";
		int rank = getRank(1971, "Frank", "M", resource);
		System.out.println(rank);
	}
	
	//TEST TOTAL BIRTH
	public static void testTotalBirth() {
			FileResource file = new FileResource(yearly+"yob1905.csv");
			totalBirth(file);
		}
		
	
	
	public static void main(String[] args) {
//		printNames();
	//	testTotalBirth();
		//testRank();
		//testGetName();
		//testNameYear();
		//testHighYear();
		//testAvgRank();
		testBirthSum();
	}

}
