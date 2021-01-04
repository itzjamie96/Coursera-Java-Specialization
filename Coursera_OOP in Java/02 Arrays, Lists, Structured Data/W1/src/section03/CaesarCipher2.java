package section03;

public class CaesarCipher2 {

	private static String alphabet;
	private static String shift1;
	private static String shift2;
	private static int key1;
	private static int key2;
	
	public CaesarCipher2(int k1, int k2) {
		alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		key1 = k1;
		key2 = k2;
		
		for(int i=0; i<alphabet.length();i++) {
			if(i%2==0) shift1 = alphabet.substring(k1)+alphabet.substring(0, k1);
			else shift2 = alphabet.substring(k2)+alphabet.substring(0, k2);
		}
		
	}
	
	
	//ENCRYPT
	public String encrypt(String input) {
		
		StringBuilder sb = new StringBuilder(input);
		
		int idx=0;
		
		for(int i=0; i<sb.length();i++) {
			if(i%2==0) {
				char c = sb.charAt(i);
				
				if(Character.isLowerCase(c)) 
					idx = alphabet.indexOf(Character.toUpperCase(c));				
				else idx = alphabet.indexOf(c);
				
				if(idx != -1) {
					if(Character.isLowerCase(c)) 
						c = Character.toLowerCase(shift1.charAt(idx));
					else c = shift1.charAt(idx);
					sb.setCharAt(i, c);
				}
			}
			else if (i%2==1) {
				char c = sb.charAt(i);
				if(Character.isLowerCase(c)) 
					idx = alphabet.indexOf(Character.toUpperCase(c));				
				else idx = alphabet.indexOf(c);
				
				if(idx != -1) {
					if(Character.isLowerCase(c)) 
						c = Character.toLowerCase(shift2.charAt(idx));
					else c = shift2.charAt(idx);
					sb.setCharAt(i, c);
				}
			}
		}
		
		
		return sb.toString();
		
	}
	
	//DECRYPT
	public String decrypt(String input) {
		CaesarCipher2 cc = new CaesarCipher2(26-key1, 26-key2);
		return cc.encrypt(input);
	
	}
	/*
	public static void main(String[] args) {
		CaesarCipher2 cc = new CaesarCipher2(17, 3);
		System.out.println(cc.encrypt("FREE"));;
	}
	*/


}
