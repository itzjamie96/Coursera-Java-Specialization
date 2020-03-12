package section02;

import java.util.Arrays;

import edu.duke.FileResource;
import section01.Test02_Caesar;

public class Test02_decrypt {

	static Ex03_occurences solve = new Ex03_occurences();
	
	//HALF SPLIT STRING
	public static String halfOfString(String msg, int start) {
		
		String split = "";
		
		for(int i=start; i<msg.length();i+=2) {
			split += msg.charAt(i);
		}
		
		return split;
	}
	
	//GET THE KEY
	public static int getKey(String s) {
		int[] freq = solve.countOccur(s);
		int largeFreq = solve.maxIndex(freq);
		
		
		
		int key = largeFreq -4;
		
		if(largeFreq<4) key = 26-(4-largeFreq);
//		if(largeFreq<0) {
//			key = largeFreq + 26;
//		}
		
		return key;
	}
	
	//DECRYPT TWO KEYS
	public static String decrypTwoKeys(String enc) {
		
		String half1 = halfOfString(enc, 0);
		String half2 = halfOfString(enc, 1);
		
		System.out.println("first half: "+half1);
		System.out.println("second half: "+half2);
		
		int k1 = getKey(half1);
		int k2 = getKey(half2);
		
		System.out.println("k1: "+k1+"\tk2: "+k2);
		
		String a = Test02_Caesar.encrypt(half1, 26-k1);
		String b = Test02_Caesar.encrypt(half2, 26-k2);
		
		System.out.println(a);
		System.out.println(b);
		
//		System.out.println(enc.length());

		String[] result = new String[enc.length()];
		
		for(int i=0; i<result.length; i++) {
			if(i%2==0) {
				for(int j=0; j<a.length();j++) {
					if(i==0) result[0] = Character.toString(a.charAt(0));
					else if(i/2==j) result[i] = Character.toString(a.charAt(j));
					}
			 }
			if(i%2==1) {
				for(int j=0; j<b.length(); j++) {
					if(i==j*2+1) result[i] = Character.toString(b.charAt(j));
				}
			}

		}
		for(int i=0; i<result.length;i++) {
			System.out.print(result[i]);
		}
//		System.out.println(Arrays.toString(result));

		return "";
	}
	
	//TEST TWO KEY
	public static void testTwoKey() {
//		String test = "Just a test string with lots of eeeeeeeeeeeeeeeees";
//		String enc = Test02_Caesar.encryptTwoKeys(test,23,2);
		
		FileResource fr = new FileResource();
		String enc = fr.asString();
		
//		String enc = "Top ncmy qkff vi vguv vbg ycpx";
		
//		System.out.println("sentence: "+test);
		System.out.println("encrypted sent: "+enc);
		
		
		String result = decrypTwoKeys(enc);
		System.out.println(result);
	}
	
	//TEST GET KEY
	public static void testGetKey() {
//		String enc = testEncrypt();
		String enc = "Just a test string with lots of eeeeeeeeeeeeeeeees";
		int key = getKey(enc);
		System.out.println(key);
	}
	
	//TEST HALF STRING
	public static void testHalfString() {
		System.out.println(halfOfString("Qbkm Zgis", 0));
		System.out.println(halfOfString("Qbkm Zgis", 1));
	}
	
	//TEST DECRYPT
	public static void testDecrypt() {
		System.out.println(solve.decrypt("IUHH"));
	}
	
	//TEST ENCRYPT
	public static String testEncrypt() {
		String ans = Test02_Caesar.encryptTwoKeys("Just a test string with lots of eeeeeeeeeeeeeeeees", 23, 2);
		return ans;
	}
	
	public static void main(String[] args) {
//		testDecrypt();
//		testHalfString();
//		testEncrypt();
//		testGetKey();
		testTwoKey();
		
		

	}

}
