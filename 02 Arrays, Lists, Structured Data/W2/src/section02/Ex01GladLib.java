package section02;

import edu.duke.*;
import java.util.*;

public class Ex01GladLib {
	private ArrayList<String> adjectiveList;
	private ArrayList<String> nounList;
	private ArrayList<String> colorList;
	private ArrayList<String> countryList;
	private ArrayList<String> nameList;
	private ArrayList<String> animalList;
	private ArrayList<String> timeList;
	
	//ADD VERB AND FRUIT
	private ArrayList<String> verbList;
	private ArrayList<String> fruitList;
	
	//TRACK USED WORDS
	private ArrayList<String> used;
	
	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "C:\\Git\\Coursera-Java-Specialization\\02 Arrays, Lists, Structured Data\\data\\GladLibData\\data\\";
	
	//CONSTRUCTOR
	public Ex01GladLib(){
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
		used = new ArrayList<String>();
	}
	
	public Ex01GladLib(String source){
		initializeFromSource(source);
		myRandom = new Random();
		used = new ArrayList<String>();
	}
	
	//생성자에서 초기값을 주기 애매해서(파일이라) 초기화시키는 메소드 구현!
	private void initializeFromSource(String source) {
		adjectiveList= readIt(source+"/adjective.txt");	
		nounList = readIt(source+"/noun.txt");
		colorList = readIt(source+"/color.txt");
		countryList = readIt(source+"/country.txt");
		nameList = readIt(source+"/name.txt");		
		animalList = readIt(source+"/animal.txt");
		timeList = readIt(source+"/timeframe.txt");		
		verbList = readIt(source+"/verb.txt");		
		fruitList = readIt(source+"/fruit.txt");		
	}
	
	//GET RANDOM VALUE
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	//SUBSTITUTE WITH THE RANDOM VALUE
	private String getSubstitute(String label) {
		if (label.equals("country")) {
			return randomFrom(countryList);
		}
		if (label.equals("color")){
			return randomFrom(colorList);
		}
		if (label.equals("noun")){
			return randomFrom(nounList);
		}
		if (label.equals("name")){
			return randomFrom(nameList);
		}
		if (label.equals("adjective")){
			return randomFrom(adjectiveList);
		}
		if (label.equals("animal")){
			return randomFrom(animalList);
		}
		if (label.equals("timeframe")){
			return randomFrom(timeList);
		}
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		//ADD VERB AND FRUIT
		if (label.equals("verb")) {
			return randomFrom(verbList);
		}
		if (label.equals("fruit")) {
			return randomFrom(fruitList);
		}
		
		return "**UNKNOWN**";
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
	
	//CREATE TEMPLATE WITH NEW WORDS
	public void makeStory(){
//	    System.out.println("\n");
		
		//CLEAR OUT USED WORDS BEFORE CREATING TEMPLATE
		used.clear();
		
		String story = fromTemplate("C:\\Git\\Coursera-Java-Specialization\\02 Arrays, Lists, Structured Data\\data\\GladLibData\\data\\madtemplate2.txt");
		printOut(story, 60);
	}
	
	public static void main(String[] args) {
		Ex01GladLib test = new Ex01GladLib();
		test.makeStory();
		
	}

}
