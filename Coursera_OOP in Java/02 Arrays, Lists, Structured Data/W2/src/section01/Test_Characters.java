package section01;

import java.util.ArrayList;

import edu.duke.FileResource;

public class Test_Characters {

	private ArrayList<String> names;
	private ArrayList<Integer> count;
	
	public Test_Characters() {
		names = new ArrayList<String>();
		count = new ArrayList<Integer>();
	}
	
	public void update(String person) {
		//update both arrays by adding unique name
		
		int idx = names.indexOf(person);
		
		if(idx == -1) {
			names.add(person);
			count.add(1);
		}
		
		else {
			int val = count.get(idx);
			count.set(idx, val+1);
		}
		
	}
	
	public void findAllCharacters() {
		FileResource file = new FileResource();
		
		names.clear();
		count.clear();
		
		for(String line : file.lines()) {
			if(line.contains(".")) {
				int idx = line.indexOf(".");
				String person = line.substring(0, idx);
				update(person);
			}
		}
	}
	
	public void charactersWithNumParts(int num1, int num2) {
		for(int i=0; i<count.size(); i++) {
			if(count.get(i)>=num1 && count.get(i)<=num2)
				System.out.println(names.get(i));
		}
	}
	
	public void tester() {
		findAllCharacters();
		
		for(int i=0; i<count.size(); i++) {
			if(count.get(i)>1)
				System.out.println(names.get(i)+" "+count.get(i));
		}
		
		charactersWithNumParts(10, 15);
	}
	
	public static void main(String[] args) {
		Test_Characters test = new Test_Characters();
		test.tester();

	}

}
