package section02;

import java.util.Arrays;

import edu.duke.FileResource;

public class Test01_wordLength {

	//COUNT WORD LENGTH
	/*
	
	�� �ܾ��� ���� ���ؼ� (n), ���̰� n�� �ֵ��� ������ ���ؼ�(k) ũ�Ⱑ k��ŭ�� �迭 ����. 
	n�� �ִ밪���� �迭�� ����
	�� ���̿� �ش��ϴ� �ܾ ������ �ش�Ǵ� ���� ������Ű��
	Ex. I am a bad guy.  
		1  2 1  3	3	<< ���� 
		
		- �迭 �ִ� ���� = 3
		int[] arr = new arr[3]
		arr : [0,0,0]
		
		- ���� �� ������ �ܾŭ count
		arr : [2,1,2]
		
	�̷����ε�?
	*/
	
	public static void countWordLength(FileResource fr, int[] count) {
		
//		int max = 0;
		
		//������ �� �ܾ� �б�		
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
