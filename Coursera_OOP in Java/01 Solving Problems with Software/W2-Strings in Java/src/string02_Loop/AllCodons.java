package string02_Loop;

public class AllCodons {
	
	public static int findStopCodon(String dna, int startIndex, String stopCodon) {
		
		//find stopCodon starting from (startIndex + 3) = currIndex
		int currIndex = dna.indexOf(stopCodon, startIndex+3);
		
		//as long as currIndex != -1
		while (currIndex != -1) {
			//Check if (currIndex - startIndex) is 3의 배수
			//If so, the text between startIndex & currIndex is the answer
			int diff = currIndex - startIndex;
			if (diff%3==0) {
				return currIndex;
			}
			//If not, update currIndex to look for stopCodon from currIndex+1
			else {
				currIndex = dna.indexOf(stopCodon, currIndex+1);
			}
		}
		//if we exit the loop, we didn't find stopCodon
		//so return dna.lenth()
		return dna.length();
	}
	
	public static String findGene(String dna) {
		
		//find first occurrence of "ATG" = startIndex
		int startIndex = dna.indexOf("ATG");
		
		//if startIndex = -1, return ""
		if (startIndex == -1) return "";
		
		//use taaIndex to store findStopCodon(dna, startIndex, "")
		int taaIndex = findStopCodon(dna, startIndex, "TAA");
		int tagIndex = findStopCodon(dna, startIndex, "TAG");
		int tgaIndex = findStopCodon(dna, startIndex, "TGA");
		
		//store in minIndex the smallest of these three values
		int tmp = Math.min(taaIndex, tagIndex);
		int minIndex = Math.min(tmp, tgaIndex);
		
		//if minIndex is dna.length = nothing found, return ""
		if(minIndex == dna.length()) return "";
		
		//otherwise answer is text from startIndex to minIndex
		return dna.substring(startIndex,minIndex+3);
		

	}
	
	public static void main(String[] args) {
		
		//testing findStop
		
		//index = 	  01234567890123456789012345
		String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
		
		int dex = findStopCodon(dna, 0, "TAA");
		if(dex!=9) System.out.println("error on 9");
		
		dex = findStopCodon(dna, 9, "TAA");
		if(dex!=21) System.out.println("error on 21");
		
		dex = findStopCodon(dna, 1, "TAA");
		if(dex!=26) System.out.println("error on 26");
		
		dex = findStopCodon(dna, 0, "TAG");
		if(dex!=26) System.out.println("error on 26 TAG");
		
		System.out.println("tests finished");
		
	}
	
}
