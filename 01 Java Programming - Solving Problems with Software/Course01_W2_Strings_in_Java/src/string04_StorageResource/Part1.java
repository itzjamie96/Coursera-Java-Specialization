package string04_StorageResource;

import edu.duke.*;

public class Part1 {

	public static int findStopCodon(String dna, int startIndex, String stopCodon) {
		
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
		
		StorageResource sr = new StorageResource();
		
		FileResource fr = new FileResource("C:\\workspace\\Coursera_OOP\\Course01_W2_Strings_in_Java\\src\\string04_StorageResource\\brca1line.fa");
		String dna = fr.asString().toUpperCase();
		
		System.out.println(findGene(dna, 0));
		
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
	
	public static StorageResource getAllGenes(String dna) {
		
		System.out.println("getAllGenes dna: "+dna);
		
		StorageResource geneList = new StorageResource();
		
		int start = 0;
		
		while (true) {
			String gene = findGene(dna, start);
			if(gene.isEmpty()) break;
			geneList.add(gene);
			System.out.println(gene);
			start = dna.indexOf(gene, start)+gene.length();
			System.out.println("updated start"+start);
		}
		//for (String s : geneList.data()) System.out.println("geneList: "+s);
		return geneList;
		
	}
	
////////////////////////////////////////////////////////////////////////
	
	//rratio of C and G in a DNA
	public static float cgRatio(String dna) {

		float total = dna.length();

		int count = 0;
		
		for (int i=0; i<total; i++) {
			if (dna.charAt(i)=='C' || dna.charAt(i)=='G') 
				count++;
		}

		System.out.println("ratio:"+(count/total));
		
		return count/total;
	}
	
	public static void processGenes(StorageResource sr) {
		
		System.out.println("\n***processGenes start***\n");
		
		//print all the Strings in sr that are longer than 9 characters	
		int characters=0;
		int ratio=0;
		int length=0;
		
		for(String s : sr.data()) {
			if (s.length()>60) {
				System.out.println("strings 60+: "+s);
				characters++;
			}
			
			//print the Strings in sr whose C-G-ratio is higher than 0.35
			if(cgRatio(s)>0.35) {
				System.out.println("cgratio 0.35+: "+s);
				ratio++;
			}
			
			if(s.length()>length) length = s.length();
			
		}
		//print the number of Strings in sr that are longer than 9 characters
		System.out.println("count 60+: "+characters);
		
		//print the number of strings in sr whose C-G-ratio is higher than 0.35
		System.out.println("count ratio 0.35+: "+ratio);
		
		//print the length of the longest gene in sr
		System.out.println("longest lenght: "+length);
		
	}
	
	public static void testProcessGenes() {
		
		StorageResource sr = new StorageResource();
		
		FileResource fr = new FileResource("C:\\workspace\\Coursera_OOP\\Course01_W2_Strings_in_Java\\src\\string04_StorageResource\\brca1line.fa");
		String dna = fr.asString().toUpperCase();
		sr = getAllGenes(dna);
		processGenes(sr);
	}
	
	public static void main(String[] args) {
//		printAllGenes("ATGcccTAGccATGcccTAAcccATGTAA");
//		getAllGenes("ATGcccTAGccATGcccTAAcccATGTAA");
//		cgRatio("ATGCCATAG");

		testProcessGenes();
	}

}
