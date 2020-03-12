package string02_Loop;

public class Part1 {

	/*
	 * A. Find a gene in a strand of DNA where the stop codon could 
	 * be any of the three stop codons “TAA”, “TAG”, or “TGA”.
	 * 
	 * B. Find all the genes in a strand of DNA.
	 */

	public static int findStopCodon(String dna, int startIndex, String stopCodon) {
		//startIndex : where the first occurrence of ATG occurs in dna
		//returns the index of stopCodon's first occurrence && 3의배수 from startIndex
		//if no stopCodon, returns the length of dna strand
		
		//stopCodon의 인덱스 : ATG ~ stopCodon
		int currIndex = dna.indexOf(stopCodon, startIndex+3);	//startIndex+3 잊지말기 ATG니까
//		System.out.println("currIndex: "+currIndex);
		
		//currIndex 가 -1이 아니다 = gene이 존재함
		//ATG - TAA 가 3의 배수일때 TAA의 인덱스 반환
		while (currIndex != -1) {
			int diff = currIndex - startIndex;
//			System.out.println("diff: "+diff);
			
			if (diff%3==0) {
//				System.out.println("3의배수 - currIndex:" + currIndex);
				return currIndex;
			} 
			
			//3의 배수가 아니라면 그 뒤에 더 있는지 확인해야함
			//stopCodon 찾아보기 & 시작은 currIndex+1에서
			else {
//				System.out.println("diff%3="+diff%3);
				currIndex = dna.indexOf(stopCodon,currIndex+1);
//				System.out.println("updated currIndex: "+currIndex);
			} 
		}
		//System.out.println("No Gene in "+dna);
		return dna.length();
		
	}
	//testFindStopCodon : call findStopCodon with examples + print results
	public static void testFindStopCodon() {
		String a = "ATGccTAAccccTAA";
		//답은 11나와야함
		System.out.println("ATGccTAAccccTAA: "+findStopCodon(a, 0, "TAA"));
		
		String b = "ATGcccTAGccTAG";
		System.out.println("ATGcccTAGccTAG: "+findStopCodon(b, 0, "TAA"));
		

	}
	
	public static String findGene(String dna, int where) {
		
		//Find the index of the first occurrence of the start codon “ATG”. 
		//If there is no “ATG”, return the empty string.
		
		//System.out.println("findGene(dna,where) : "+dna +" / "+where);
		
		int startATG = dna.indexOf("ATG",where);
		
		//System.out.println(startATG);
		if (startATG==-1) return "";

		//Find the index of the first occurrence of the stop codon “TAA”, "TAG", "TGA"
		//after the first occurrence of “ATG” that is a multiple of three away 
		//from the “ATG”. Hint: call findStopCodon.
		
		//System.out.println();
		//System.out.println("incoming dna: "+dna);
		//System.out.println("length of dna:"+dna.length());
		
		int taa = findStopCodon(dna, startATG, "TAA");
		//System.out.println("taa: "+taa);
		
		int tga = findStopCodon(dna, startATG, "TGA");
		//System.out.println("tga: "+tga);
		
		int tag = findStopCodon(dna, startATG, "TAG");
		//System.out.println("tag: "+tag);
			
		if(taa==dna.length() && tga==dna.length() && tag==dna.length()) return "";
		
		//Return the gene formed from the “ATG” and the closest stop codon 
		//that is a multiple of three away. 
		//If there is no valid stop codon and therefore no gene, return the empty string.
		
		int min = 0;
		if (taa>0) {
			if (tag>0) min = (taa>=tag)?tag:taa;
			else min=taa;
		}
		//System.out.println("taa vs tag :: min = "+min);
		if(min>0) {
			if(tga>0) min = (min>=tga)?tga:min;
		}
		//System.out.println("min vs tga :: min = "+min);
		
		if(min==-1) return "";
		else return dna.substring(startATG, min+3);
	}
	
	public static void testFindGene() {
		String a = "noatgTAA";
		String b = "ATGcccTAG";
		String c = "ATGcccTAAcccTGA";
		String d = "ATGcccTTT";
		/*
		System.out.println(a + " : "+findGene(a));
		System.out.println(b + " : "+findGene(b));
		System.out.println(c + " : "+findGene(c));
		System.out.println(d + " : "+findGene(d));
		*/
	}
	
	
	public static void printAllGenes(String dna) {

		int start = 0;

		while (true) {
			String gene = findGene(dna, start);
			if(gene.isEmpty()) break;
			System.out.println(gene);
			start = dna.indexOf(gene, start)+gene.length();
		} 
	}
	
	public static void main(String[] args) {
		//testFindStopCodon();
		//testFindGene();
		printAllGenes("ATGcccTAGccATGcccTAAcccATGTAA");
		//System.out.println(findGene("noatgTAA"));
	}

}
