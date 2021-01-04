package string04_StorageResource;

import edu.duke.*;
import string02_Loop.Part1;

public class StorageResource02 {
	
	public static StorageResource getAllGenes(String dna) {
		
		//declare new st with nothing in it
		StorageResource geneList = new StorageResource();
		
		int startIdx = 0;
		
		while(true) {
			
			String currGene = Part1.findGene(dna, startIdx);
			
			if(currGene.isEmpty()) break;

			geneList.add(currGene);
			
			startIdx = dna.indexOf(currGene, startIdx)+currGene.length();
		
		}
		
		return geneList;
		 
	}
	
	public static void test(String dna) {
		System.out.println("Testing getAllGenes with storageResource");
		StorageResource sr = getAllGenes(dna);
		
		for (String s : sr.data()) System.out.println(s); 
		
	}
	
	public static void main(String[] args) {
		test("ATGcccTAGccATGcccTAAcccATGTAA");
	}
	
}
