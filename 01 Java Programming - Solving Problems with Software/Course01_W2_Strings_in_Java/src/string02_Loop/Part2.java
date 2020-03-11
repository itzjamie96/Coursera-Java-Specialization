package string02_Loop;

public class Part2 {

	private static int howMany(String word, String sentence) {
		//how many times a appear in b
		//no overlaps
		
		//시작하는 인덱스 값
		int startIdx = 0;
		int count = 0;
		
		//단어의 첫 인덱스
		int currIdx = sentence.indexOf(word, startIdx);
		
		//currIdx 가 -1이 아닐동안 = 존재할동안
		while (currIdx != -1) {
			count++;
			currIdx = sentence.indexOf(word, currIdx+word.length());
		}
		
		return count;
	}
	
	private static void testHowMany() {
		System.out.println(howMany("GAA","ATGAACGAATTGAATC"));
		System.out.println(howMany("abc","werertabcweraabcereaaabbc"));
		System.out.println(howMany("ta","areretadfasdftatatataasdfasd"));
		
	}
	
	public static void main(String[] args) {
		testHowMany();
	}

}
