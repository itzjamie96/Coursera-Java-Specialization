package section03;

public class CaesarCipher {

	private String alphabet;
	private String shiftedAlpha;
	private int mainKey;
	
	public CaesarCipher(int key) {
		alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		shiftedAlpha = alphabet.substring(key) + alphabet.substring(0, key);
		mainKey = key;
	}
	
	public String encrypt(String input) {
		StringBuilder sb = new StringBuilder(input);
		
		int idx=0;
		for(int i=0;i<sb.length();i++) {
			char c = sb.charAt(i);
			if(Character.isLowerCase(c)) 
				idx = alphabet.indexOf(Character.toUpperCase(c));				
			else idx = alphabet.indexOf(c);
			
			if(idx != -1) {
				if(Character.isLowerCase(c)) 
					c = Character.toLowerCase(shiftedAlpha.charAt(idx));
				else c = shiftedAlpha.charAt(idx);
				sb.setCharAt(i, c);
			}
		}
		return sb.toString();
	}

	
	public String decrypt(String input) {
		CaesarCipher cc = new CaesarCipher(26-mainKey);
		return cc.encrypt(input);
	}
	
}
