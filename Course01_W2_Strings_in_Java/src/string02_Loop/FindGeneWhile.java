package string02_Loop;

public class FindGeneWhile {
	
	public String findGene(String dna) {
		
		//Find first occurrence of ATG = startIndex
		int startIndex = dna.indexOf("ATG");
		
		//find TAA starting from (startIndex+3) = currIndex
		int currIndex = dna.indexOf("TAA", startIndex+3);
		
		//as log as currIndex is not equal to -1
		while (currIndex != -1) {			
			//Check if (currIndex - startIndex) is 3의 배수
			if ((currIndex-startIndex)%3==0) {				
				//If so, the text between startIndex & currIndex is the answer
				return dna.substring(startIndex, currIndex+3);
			}			
			//If not, update currIndex to the index of the next TAA
			else {
				currIndex = dna.indexOf("TAA", currIndex+1);
			}	
		}
		//If nothing, empty String
		return "";
	}
	
	public static void main(String[] args) {
		String dna = "AATGCGTAATTAATCG";
		System.out.println("DNA is" +dna);
		
	}
}
