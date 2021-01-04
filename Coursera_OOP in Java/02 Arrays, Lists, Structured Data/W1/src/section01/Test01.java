package section01;

public class Test01 {

	//IS THE CHAR VOWEL?
	public static boolean isVowel(char ch) {
		
		String input = String.valueOf(ch).toUpperCase();
		
		if(input.contentEquals("A") || input.contentEquals("E") || input.contentEquals("I")
				|| input.contentEquals("O") || input.contentEquals("U"))
			return true;
		
		return false;
		
	}
	
	//REPLACE THE VOWELS!
	public static String replaceVowel(String phrase, char ch) {
		
		StringBuilder sb = new StringBuilder(phrase);
		
		for(int i=0; i<sb.length(); i++) {
			
			if(isVowel(sb.charAt(i))) {
				sb.setCharAt(i, ch);
			}
		}
		
		return sb.toString();
	}
	
	//EMPHASIZEEEE
	public static String emphasize(String phrase, char ch) {
		
		StringBuilder sb = new StringBuilder(phrase);

		//ch¸¦ replaceÇÏ´Â °Í
		for (int i=0; i<sb.length(); i++) {
			
			//odd location = Â¦¼ö ÀÎµ¦½º
			if(sb.charAt(i)==ch || sb.charAt(i) == Character.toUpperCase(ch)) {
				
				if(i%2==0) sb.setCharAt(i, '*');
				else sb.setCharAt(i, '+');
			}
		}	
		return sb.toString();
	}
	
	//TEST EMPHASIZE
	public static void testEmpha(){
		String a = emphasize("dna ctgaaactFA", 'f');
		String b = emphasize("Mary Bella Abracadabra", 'a');
		
		System.out.println(a);
		System.out.println(b);
	}
	
	//TEST REPLACE VOWEL
	public static void testReplace() {
		String test = replaceVowel("cccc", '*');
		System.out.println(test);
	}
	
	
	//TEST VOWEL!
	public static void testVowel() {
		boolean check = isVowel('g');
		System.out.println(check);		
	}
	
	public static void main(String[] args) {
//		testReplace();
		testEmpha();
	}

}
