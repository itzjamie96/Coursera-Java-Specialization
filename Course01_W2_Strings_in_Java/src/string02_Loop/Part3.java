package string02_Loop;

public class Part3 {

	private static int countGenes(String dna) {
		
		int start = 0;
		int count = 0;
		
		while(true) {
			String gene = Part1.findGene(dna, start);
			if(gene.isEmpty()) break;
			count++;
			start = dna.indexOf(gene, start)+gene.length();
		}
		return count;
	}
	private static void testCountGenes() {
		System.out.println(countGenes("ATGTAAGATGCCCTAGT"));
		
	}
	
	public static void main(String[] args) {
		testCountGenes();

	}

}
