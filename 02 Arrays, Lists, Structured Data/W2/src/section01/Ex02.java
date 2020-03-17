package section01;

import edu.duke.FileResource;
import edu.duke.StorageResource;

public class Ex02 {

	StorageResource myWords;
	
	public Ex02() {
		myWords = new StorageResource();
	}
	
	public void readWords() {
		myWords.clear();
		FileResource file = new FileResource();
		
		for (String s : file.words()) {
			myWords.add(s.toLowerCase());
		}
	}
	
	public boolean contains(String[] list, String word, int num) {
		
		for(int i=0; i<num; i++) {
			if(list[i].equals(word)) {
				return true;
			}
		}
		return false;
	}
	
	public int numUniqueWord() {
		//number of unique word
		int numStored = 0;
		
		//store all unique words
		String[] words = new String[myWords.size()];
		
		for(String s : myWords.data()) {
			
			if(!contains(words,s,numStored)) {
				words[numStored] = s;
				numStored++;
			}
		}
		return numStored;
	}
	
	public void tester() {
		readWords();
		System.out.println("number of words: "+myWords.size());
		int unique = numUniqueWord();
		System.out.println("array count: "+unique);
	}
	
	public static void main(String[] args) {
		Ex02 test = new Ex02();
		test.tester();

	}

}
