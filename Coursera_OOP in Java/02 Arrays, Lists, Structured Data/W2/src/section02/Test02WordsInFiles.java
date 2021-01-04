package section02;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class Test02WordsInFiles {

	private HashMap<String, ArrayList<String>> map;
	
	public Test02WordsInFiles() {
		map = new HashMap<String, ArrayList<String>>();
	}
	
	private void addWordsFromFile(File f) {
		//add all the words from file to the map
		FileResource fr = new FileResource(f);
		
		for(String s: fr.words()) {
			if(map.keySet().contains(s)) {
				ArrayList<String> FileList = map.get(s);
				if(!FileList.contains(f.getName())) 
					FileList.add(f.getName());
				map.put(s, FileList);
			}
			else {
				ArrayList<String> FileList = new ArrayList<String>();
				FileList.add(f.getName());
				map.put(s, FileList);
			}
		}
	}
	
	private void buildWordFileMap() {
		map.clear();
		
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles()) {
			addWordsFromFile(f);
		}
		
	}
	
	private int maxNum() {
		int count = 0;
		
		for(ArrayList<String> list : map.values()) {
			if(list.size()>count)
				count = list.size();
		}
		return count;
	}
	
	private ArrayList<String> wordsInNumfiles(int num){
		ArrayList<String> words = new ArrayList<String>();
		
		for(String s : map.keySet()) {
			int count = map.get(s).size();
			if(count==num)
				words.add(s);
		}
		return words;
	}
	
	private void printFilesIn(String word) {
		for(String s : map.keySet()) {
			if(s.equals(word))
				System.out.println(map.get(s).toString());
		}
	}
	
	private void tester() {
		buildWordFileMap();
		
		int max = maxNum();
		System.out.println("max file num: "+max);
		
		ArrayList<String> words = wordsInNumfiles(4);
//		System.out.println("words with max files: "+words.toString());
		System.out.println(words.size());
		
//		for(String s : words) {
//			printFilesIn(s);
//		}
		printFilesIn("tree");
		
		
	}
	
	public static void main(String[] args) {
		Test02WordsInFiles test = new Test02WordsInFiles();
		test.tester();

	}

}
