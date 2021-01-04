package section02;

import edu.duke.*;
import java.util.*;

public class Test03GladLibMap {
	HashMap<String, ArrayList<String>> map;
	
	//TRACK USED WORDS
	private ArrayList<String> used;
	private ArrayList<String> category;	
	
	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "C:\\Git\\Coursera-Java-Specialization\\02 Arrays, Lists, Structured Data\\data\\GladLibData\\data\\";
	
	//CONSTRUCTOR
	public Test03GladLibMap(){
		map = new HashMap<String, ArrayList<String>>();
		myRandom = new Random();
		initializeFromSource(dataSourceDirectory);
		used = new ArrayList<String>();
		category = new ArrayList<String>();
	}
	
	public Test03GladLibMap(String source){
		map = new HashMap<String, ArrayList<String>>();
		myRandom = new Random();
		initializeFromSource(source);
		used = new ArrayList<String>();
		category = new ArrayList<String>();
	}
	
	//생성자에서 초기값을 주기 애매해서(파일이라) 초기화시키는 메소드 구현!
	private void initializeFromSource(String source) {	
		String[] list = {"adjective", "noun", "color","country","name",
				"animal", "timeframe", "verb", "fruit"};
		
		
		for (String s : list) {
//			System.out.println(s);
			ArrayList<String> file = readIt(source+"/"+s+".txt");
			map.put(s, file);
		}
		
	}
	
	//GET RANDOM VALUE
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	//SUBSTITUTE WITH THE RANDOM VALUE
	private String getSubstitute(String label) {
		
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}

		return randomFrom(map.get(label));
	}
	
	//READ THE TEMPLATE TO FIND THE LABEL
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		
		//<괄호>찾을 수 없으면 텍스트 그대로 리턴
		if (first == -1 || last == -1){
			return w;
		}
		
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String sub = getSubstitute(w.substring(first+1,last));	
		
		//중복 방지
		used.add(sub);
		
		for(int i=0; i<used.size(); i++) {
			if(sub.contentEquals(used.get(i)))
				sub = getSubstitute(w.substring(first+1,last));
		}
		//TRACK CATEGORY
		if(!category.contains(w.substring(first+1,last)))
			category.add(w.substring(first+1,last));
		
		
		return prefix+sub+suffix;
	}
	
	//PRINT THE TEMPLATE?
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	//FINDING TEMPLATE / READING TEMPLATE
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	//READING THE TEMPLATE
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	public int totalWordsInMap() {
		int count = 0;
		
		for(ArrayList<String> words : map.values()) {
			count += words.size();
		}
		
		return count;
	}
	
	public int totalWordsConsidered() {
		
		int count = 0;
		
		for(int i=0;i<category.size();i++) {
			count += map.get(category.get(i)).size();
		}
		return count;
	}
	
	//CREATE TEMPLATE WITH NEW WORDS
	public void makeStory(){
//	    System.out.println("\n");
		
		//CLEAR OUT USED WORDS BEFORE CREATING TEMPLATE
		used.clear();
		
		String story = fromTemplate("C:\\Git\\Coursera-Java-Specialization\\02 Arrays, Lists, Structured Data\\data\\GladLibData\\data\\madtemplate3.txt");
		printOut(story, 60);
		
		System.out.println();
		System.out.println(totalWordsInMap());
		System.out.println(totalWordsConsidered());
	}
	
	public static void main(String[] args) {
		Test03GladLibMap test = new Test03GladLibMap();
		test.makeStory();
	
		
	}

}
