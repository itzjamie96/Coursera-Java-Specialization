package section02;

import java.util.HashMap;

import edu.duke.FileResource;

public class Test01CodonCount {

	/*
	 * how many times each codon occurs in a strand of DNA
	 * Codon: three consecutive symbols of C, G, T, A
	 */
	
	private HashMap<String, Integer> map;
	
	//CONSTRUCTOR
	public Test01CodonCount() {
		map = new HashMap<String, Integer>();
	}
	
	//PUT CODONS IN MAP
	public void buildCodonMap(int start, String dna) {
		map.clear();
		
		for(int i=start;i<dna.length();i+=3) {

			if(i+3>=dna.length()) break;
			else {
				
				String codon = dna.substring(i,i+3);
				
//				System.out.println("codon found: "+codon);
				
				if(map.keySet().contains(codon))
					map.put(codon, map.get(codon)+1);
				else
					map.put(codon, 1);			
			}
		}

	}
	
	public String getMostCommonCodon() {
		int count = 0;
		String codon = "";

		for(String s : map.keySet()) {
			int value = map.get(s);
			if(value>count) {
				count = value;
				codon = s;
			}
		}
				
		return codon;
	}
	
	public void printCodonCount(int start, int end) {
		for(String s: map.keySet()) {
			if(map.get(s)>=start && map.get(s)<=end)
				System.out.println(map.get(s)+" "+s);
		}
	}
	
	public void tester() {
		FileResource file = new FileResource();
		String dna = file.asString().toUpperCase();
		
		for(int i=0;i<3;i++) {
			System.out.println("reading frame "+i+"~"+(i+3));
			
			buildCodonMap(i, dna);
			//total number of codons
			System.out.println("total unique codons: "+map.size());
			
			//most common codon
			String mostCommon = getMostCommonCodon();
			System.out.println("most common codon: "+mostCommon+"\t"+map.get(mostCommon));
			
			//range
			printCodonCount(1, 5);
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		Test01CodonCount test = new Test01CodonCount();
		test.tester();

	}

}
