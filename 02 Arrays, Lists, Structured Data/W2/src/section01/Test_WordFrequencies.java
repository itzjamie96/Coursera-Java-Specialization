package section01;

import java.util.ArrayList;

import edu.duke.FileResource;

public class Test_WordFrequencies {

	private ArrayList<String> myWords;
	private ArrayList<Integer> myFreqs;
	//the kth position in myfreqs should represent the number of times 
	//the kth word in myWords occurs in the file
	
	public Test_WordFrequencies() {
		myWords = new ArrayList<String>();
		myFreqs = new ArrayList<Integer>();
	}
	
	public void findUnique() {
		
		//clear both arraylists
		myWords.clear();
		myFreqs.clear();
		
		FileResource file = new FileResource();
		
		for (String s : file.words()) {
			s = s.toLowerCase();
			
			//if word in file is NOT found in array, add the word
			int idx = myWords.indexOf(s);
			
			if(idx == -1) {
				myWords.add(s);
				myFreqs.add(1);
			}
			//if word was FOUND in array, add the frequency
			else {
				int val = myFreqs.get(idx);
				myFreqs.set(idx, val+1);
			}
			
		}
	}
	
	public int findIndexOfMax() {
		int max = 0;
		int idx = 0;

		for(int i=0;i<myFreqs.size(); i++) {
	
			if(myFreqs.get(i)>max) {
				max = myFreqs.get(i);
				idx = i;
			}	
			else if(myFreqs.get(i)>max) System.out.println("tie");
		}

		return idx;
	}
	
	public void tester() {
		findUnique();
		System.out.println("number of unique words: "+myWords.size());
		
		for(int i=0;i<myWords.size(); i++) {
			System.out.println(myFreqs.get(i)+" "+myWords.get(i));
		}
		
		String most = myWords.get(findIndexOfMax());
		int max = myFreqs.get(findIndexOfMax());
		
		System.out.println("The word that occurs most often and its count are:"+most+" "+max);
		
	}
	
	public static void main(String[] args) {
		Test_WordFrequencies test = new Test_WordFrequencies();
		test.tester();

	}

}
