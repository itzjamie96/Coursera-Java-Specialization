package string02_Loop;

public class AllCodonsAND {
	
	public static int findStopCodon(String dna, int startIndex, String stopCodon) {
		
		int currIndex = dna.indexOf(stopCodon, startIndex+3);
		
		while (currIndex != -1) {

			int diff = currIndex - startIndex;
			if (diff%3==0) {
				return currIndex;
			}

			else {
				currIndex = dna.indexOf(stopCodon, currIndex+1);
			}
		}

		return -1;
	}
	
	public static String findGene(String dna) {
		
		int startIndex = dna.indexOf("ATG");
		
		if (startIndex == -1) return "";
		
		int taaIndex = findStopCodon(dna, startIndex, "TAA");
		int tagIndex = findStopCodon(dna, startIndex, "TAG");
		int tgaIndex = findStopCodon(dna, startIndex, "TGA");
		
		int minIndex = 0;
		
		//taa, tag, tga 값 비교 
		if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
			minIndex = tgaIndex;
		} else {
			minIndex = taaIndex;
		}
		
		if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
			minIndex = tagIndex;
		}
		
		if(minIndex == -1) return "";
		
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
		if(dex!=-1) System.out.println("error on 26");
		
		dex = findStopCodon(dna, 0, "TAG");
		if(dex!=-1) System.out.println("error on 26 TAG");
		
		System.out.println("tests finished");
		
	}
	
}
