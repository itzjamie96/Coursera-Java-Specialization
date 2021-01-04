package section02;

import edu.duke.FileResource;

public class Ex02_shakespear {

	public static void countShakes() {
//		String[] plays = {"caesar.txt", "errors.txt", "hamlet.txt", "likeit.txt", "macbeth.txt", "romeo.txt"};
	
		//testing with small data
		String[] plays = {"small.txt"};
		
		String[] common = getCommon();
		
		int[] counts = new int[common.length];
		
		for(int i=0; i<plays.length; i++) {
			FileResource fr = new FileResource("C:\\Git\\Coursera-Java-Specialization\\02 Arrays, Lists, Structured Data\\data\\"+plays[i]);
			countWords(fr, common, counts);
			System.out.println("done with " + plays[i]);
		}
		for(int i=0; i<common.length; i++) {
			System.out.println(common[i]+"\t"+counts[i]);
		}
	}
	
	//getting the common words
	public static String[] getCommon() {
		
		FileResource fr = new FileResource("C:\\Git\\Coursera-Java-Specialization\\02 Arrays, Lists, Structured Data\\data\\common.txt");
		String[] common = new String[20];
		
		int idx = 0;
		//read the file and put the words in common[]
		for(String s:fr.words()) {
			common[idx] = s;
			idx++;
		}
		return common;
	}
	
	public static int indexOf(String[] list, String word) {
		//where does the common word appear? = LOCATION
		
		for(int i=0; i<list.length; i++) {
			if(list[i].equals(word)) return i;
		}
		//그 단어가 없으면 -1
		return -1;
	}
	
	
	//COUNT WORDS
	public static void countWords(FileResource fr, String[] common, int[] counts) {
		
		for(String word: fr.words()) {
			word = word.toLowerCase();
			int idx = indexOf(common, word);
			
			if(idx != -1) counts[idx]++;
		}
		
	}
	
	public static void main(String[] args) {
		countShakes();

	}

}
