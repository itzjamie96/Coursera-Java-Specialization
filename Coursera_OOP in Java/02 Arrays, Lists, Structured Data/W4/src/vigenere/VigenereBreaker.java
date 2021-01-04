package vigenere;

import java.io.File;
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
        
//        System.out.println(Arrays.toString(key));
        
        return key;
    }

    //BREAK 
    public void breakVigenere () {
    	VigenereBreaker v = new VigenereBreaker();
//    	FileResource fr = new FileResource();
//		HashSet<String> dict = v.readDictionary(fr);
    	
    	HashMap<String, HashSet<String>> dicts = new HashMap<String, HashSet<String>>();
    	
    	FileResource file = new FileResource();
    	String text = file.asString();   

    	DirectoryResource dr = new DirectoryResource();
    	for (File f : dr.selectedFiles()) {
    		FileResource fr = new FileResource(f);
  
    		dicts.put(f.getName(), v.readDictionary(fr));
    		
    	}
		
            
    	String de = v.breakForAllLang(text, dicts);
    	
    	System.out.println(de);
//        String de = v.breakForLang(text, dict);
        
    }
    
    //READ DICTIONARY
    public HashSet<String> readDictionary(FileResource fr) {
    	HashSet<String> set = new HashSet<String>();
    	for(String s : fr.lines()) 
    		set.add(s.toLowerCase());
       	
    	return set;

    }
    
    //COUNT WORDS
    public int countWords(String msg, HashSet<String> dict) {
    	int count = 0;
    	
    	for(String s : msg.split("\\W+")) {	
    		if(dict.contains(s.toLowerCase())) {
    			count++;
//    			System.out.println(s);
    		}
    	}
    	return count;
    }
    
    //BREAK FOR LANGUAGE
    public String breakForLang(String enc, HashSet<String> dict) {
    	VigenereBreaker vb = new VigenereBreaker();
    	int count = 0;
    	String msg = "";
    	int[] finKeys = null;
    	
    	char common = vb.mostCommonChar(dict);
    	    	
    	for(int i=1; i<=100; i++) {
    		int[] keys = vb.tryKeyLength(enc, i, common);
    		VigenereCipher vc = new VigenereCipher(keys);
    		String dec = vc.decrypt(enc);
    		
    		if(countWords(dec, dict)>count) {
    			count = countWords(dec, dict);
    			msg = dec;   
    			finKeys = keys;
    		}
    	}    	
    	System.out.println("keys: "+Arrays.toString(finKeys));
    	System.out.println("keys length: "+finKeys.length);
    	System.out.println("count :"+count);
    	System.out.println();
    	return msg;
    }
    
    //MOST COMMON CHAR
    public Character mostCommonChar(HashSet<String> dict) {
    	char most = 0;
    	int max = 0;
    	
    	HashMap<Character, Integer> count = new HashMap<Character, Integer>();
    	for(String s : dict) {
    		char[] alpha = s.toCharArray();
    		
    		for(Character c : alpha) {
    			if(!count.containsKey(c)) {
    				count.put(c, 1);
    			} else {
    				count.put(c, count.get(c)+1);
    			}
    		}
    	}
    	for(Character c : count.keySet()) {
    		if(count.get(c)>max) {
    			max = count.get(c);
    			most = c;    			
    		}
    	}
    	System.out.println("most common: "+most);
    	return most;
    }
    
    // FOR ALL LANGS
    public String breakForAllLang(String enc, HashMap<String, HashSet<String>> lang) {
		
    	VigenereBreaker vb = new VigenereBreaker();
    	String dec = null;
    	
    	for(String s : lang.keySet()) {
    		HashSet<String> dict = lang.get(s);
    		System.out.println(s);
    		dec = vb.breakForLang(enc, dict);
    	}
    	
    	return dec;
    	
    }

    public static void main(String[] args) {
		VigenereBreaker v = new VigenereBreaker();
		v.breakVigenere();

//    	FileResource fr = new FileResource();
//		HashSet<String> dict = v.readDictionary(fr);
//		char c = v.mostCommonChar(dict);
//		System.out.println(c);
		
				
	}
}
