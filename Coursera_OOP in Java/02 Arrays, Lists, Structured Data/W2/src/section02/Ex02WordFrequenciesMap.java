package section02;

import java.util.HashMap;

import edu.duke.FileResource;

public class Ex02WordFrequenciesMap {

	public void countWords() {
		FileResource file = new FileResource();
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		int total = 0;
		
		for(String s : file.words()) {
			s = s.toLowerCase();
			
			//is the word s in my keyset?
			if(map.keySet().contains(s)) {
				
				//if yes, increase the integer value associated with it
				map.put(s, map.get(s)+1);
			} else {
				map.put(s, 1);
			}
			
		}
		for(String s: map.keySet()) {
			int occurrences = map.get(s);
			
			//if occurrences is big, print
			if(occurrences>200) {
				System.out.println(occurrences+"\t"+s);
			}
		}
	}
	
	public static void main(String[] args) {
		Ex02WordFrequenciesMap test = new Ex02WordFrequenciesMap();
		test.countWords();

	}

}
