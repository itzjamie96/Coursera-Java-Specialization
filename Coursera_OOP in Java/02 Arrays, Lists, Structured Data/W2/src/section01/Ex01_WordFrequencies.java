package section01;

import java.util.ArrayList;

import edu.duke.FileResource;

//WordFrequencies
public class Ex01_WordFrequencies {

	ArrayList<String> myWords;
	ArrayList<Integer> myFreqs;
	
	public Ex01_WordFrequencies() {
		myWords = new ArrayList<String>();
		myFreqs = new ArrayList<Integer>();
	}
	
	public void findUnique() {
		FileResource resource = new FileResource();
		
		for(String s: resource.words()) {
			s = s.toLowerCase();
			int idx = myWords.indexOf(s);
			
			//if i've never seen the word before, add it to the list
			if(idx == -1) {
				myWords.add(s);
				myFreqs.add(1);
			}
			//if I've seen the word, get the index of THAT word in my list
			//and increase 1 of that value
			else {
				int value = myFreqs.get(idx);
				myFreqs.set(idx, value+1);
			}
		}
	}
	
	public void tester() {
		findUnique();
		System.out.println("# of unique words: "+myWords.size());
		
		for(int i=0; i<myWords.size();i++) {
			System.out.println(myFreqs.get(i)+"\t"+myWords.get(i));
		}
	}
	
	public static void main(String[] args) {
		Ex01_WordFrequencies test = new Ex01_WordFrequencies();
		test.tester();

	}

}
