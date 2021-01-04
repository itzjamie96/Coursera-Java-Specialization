package section02;

import java.util.Arrays;

import edu.duke.FileResource;

public class Test01_wordLength {

	//COUNT WORD LENGTH
	/*
	
	각 단어의 길이 구해서 (n), 길이가 n인 애들의 개수를 구해서(k) 크기가 k만큼의 배열 생성. 
	n의 최대값으로 배열을 생성
	각 길이에 해당하는 단어가 있으면 해당되는 길이 증가시키기
	Ex. I am a bad guy.  
		1  2 1  3	3	<< 길이 
		
		- 배열 최대 길이 = 3
		int[] arr = new arr[3]
		arr : [0,0,0]
		
		- 이제 각 길이의 단어만큼 count
		arr : [2,1,2]
		
	이런식인듯?
	*/
	
	public static void countWordLength(FileResource fr, int[] count) {
		
//		int max = 0;
		
		//파일의 각 단어 읽기		
		for (String word : fr.words()) {
			int length = 0;
						
			for(int i=0; i<word.length(); i++) {
				if(Character.isLetter(word.charAt(i))) 
					length++;				
				else if(i < word.length()-1 && Character.isLetter(word.charAt(i)) == false)
					length++;
			}
//			System.out.println(word +" / "+length);
			
			for (int i=0; i<count.length; i++) {
				if(i==length)
					count[i] ++;
			}
		}
//		System.out.println(Arrays.toString(count));
		for(int i=0; i<count.length; i++) {
			if(count[i]>0) {
				
				System.out.println(count[i]+" words of length "+i);
			}
		}
		
	}
	//INDEX OF MAX
	public static int indexOfMax(int[] values) {
		
		int max = 0;
		int maxIdx = 0;
		
		for(int i=0; i<values.length; i++) {
			if(values[i]>max) {
				max = values[i];
				maxIdx = i;
			}
		}
		
		return maxIdx;
	}
	
	//TEST COUNT WORD LENGTH
	public static void testCountWordLength() {
		FileResource fr = new FileResource();
		int[] arr = new int[31];
		countWordLength(fr, arr);
		
		int maxIdx = indexOfMax(arr);
		System.out.println("most common word length: "+maxIdx);
		
	}
	
	public static void main(String[] args) {
		testCountWordLength();


	}

}
