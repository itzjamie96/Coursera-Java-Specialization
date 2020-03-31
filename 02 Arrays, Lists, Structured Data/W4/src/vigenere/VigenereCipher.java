package vigenere;

import edu.duke.*;
import java.util.*;

public class VigenereCipher {
    CaesarCipher[] ciphers;
    
    public VigenereCipher() { }
    
    public VigenereCipher(int[] key) {
        ciphers = new CaesarCipher[key.length];
        for (int i = 0; i < key.length; i++) {
            ciphers[i] = new CaesarCipher(key[i]);
        }
    }
    
    public String encrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.encryptLetter(c));
            i++;
        }
        return answer.toString();
    }
    
    public String decrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.decryptLetter(c));
            i++;
        }
        return answer.toString();
    }
    
    public String toString() {
        return Arrays.toString(ciphers);
    }
    
    //ALPHABETS TO NUMBERS
    public int[] toNum(String text) {
    	String alpha = "abcdefghijklmnopqrstuvwxyz";
    	char[] ch = alpha.toCharArray();
    
    	char[] input = text.toCharArray();
    	int[] idx = new int[input.length];
    	
    	for(int i=0; i<input.length; i++) {
    		char abc = input[i];
//    		System.out.println(i);
    		for(int j=0; j<ch.length; j++) {
    			if(ch[j]==abc)
    				idx[i] = j;
    		}
    	}
    	
    	return idx;
    }
    public static void main(String[] args) {
    	VigenereCipher vc = new VigenereCipher();
    	String key = "flute";
    	int[] idx = vc.toNum(key);
    	System.out.println(Arrays.toString(idx));
    	
    	FileResource file = new FileResource();
    	String text = file.asString();
    	VigenereCipher v = new VigenereCipher(idx);
    	System.out.println(v.encrypt(text));
//    	System.out.println(v.decrypt(v.encrypt(text)));
    }
}
