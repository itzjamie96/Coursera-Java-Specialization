package section01;

import edu.duke.FileResource;

public class Ex02 {

	public static String encrypt(String input, int key) {
		
		String sentence = input.toUpperCase();
		
		//StringBuilder 선언 후 들어온 문자열 넣기
		StringBuilder encrypted = new StringBuilder(sentence);
		
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String shifted = alpha.substring(key)+alpha.substring(0, key);
		
		for (int i=0; i<encrypted.length(); i++) {
			
			char curChar = encrypted.charAt(i);
			
			int idx = alpha.indexOf(curChar);
			
			if(idx != -1) {
				char newChar = shifted.charAt(idx);
				encrypted.setCharAt(i, newChar);
			}
		}
		return encrypted.toString();
		
	}
	
	public static void testEncrypt() {
		int key = 17;
		FileResource fr = new FileResource();
		String msg = fr.asString();
		
		String encrypted = encrypt(msg, key);
		System.out.println(encrypted);
		
		String decrypted = encrypt(encrypted, 26-key);
		System.out.println(decrypted);
	}
	
	public static void main(String[] args) {

		testEncrypt();
	}

}
