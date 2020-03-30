package vigenere;

import edu.duke.FileResource;

public class Tester {

	public static void main(String[] args) {
		
		FileResource file = new FileResource();
		String text = file.asString();

		//CIPHER
//		CaesarCipher cipher = new CaesarCipher(4);

//		String test = cipher.encrypt(text);
//		System.out.println(test);
//		
//		System.out.println();
//		
//		String decipher = cipher.decrypt(test);
//		System.out.println(decipher);
		
		
		//CRACKER
//		CaesarCracker cracker = new CaesarCracker();
//		System.out.println(cracker.decrypt(text));
		
		
		//VIGENERE
		int[] key = {17, 14, 12, 4};
		VigenereCipher vi = new VigenereCipher(key);
		System.out.println(vi.encrypt(text));
		System.out.println("\n"+vi.decrypt(vi.encrypt(text)));
		

	}

}
