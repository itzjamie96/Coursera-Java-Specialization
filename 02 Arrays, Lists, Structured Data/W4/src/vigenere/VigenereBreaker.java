package vigenere;

import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        
    	StringBuilder sb = new StringBuilder();
    	
    	for (int i=whichSlice; i<message.length(); i+=totalSlices) {
    		sb.append(message.charAt(i));
    	}
    	
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cracker = new CaesarCracker(mostCommon);
        
        for (int i=0; i<key.length;i++) {
        	key[i] = cracker.getKey(sliceString(encrypted,i,klength));
        }
        
        System.out.println(Arrays.toString(key));
        
        return key;
    }

    public void breakVigenere () {
        FileResource file = new FileResource();
        String text = file.asString();      
        int klength = 4;
   
        int[] keys = tryKeyLength(text, klength ,'e');
        VigenereCipher vc = new VigenereCipher(keys);
        String de = vc.decrypt(text);
        System.out.println(de);
    }
    
    public static void main(String[] args) {
		VigenereBreaker v = new VigenereBreaker();
//		String test = v.sliceString("abcdefghijklm", 4, 5);
//		System.out.println(test);
		
		v.breakVigenere();
	}
}
