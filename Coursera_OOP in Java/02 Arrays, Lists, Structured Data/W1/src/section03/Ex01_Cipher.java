package section03;

public class Ex01_Cipher {

	//field: special variable living IN AN OBJECT
	private static String abc;
	private static String shift;
	//any code in this class can refer by name
	
	
	//CONSTRUCTOR: code to initialize an object when it's created
	//same name as class
	public Ex01_Cipher(int key) {
		
		//abc랑 shift초기화 하는 중
		abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		shift = abc.substring(key) + abc.substring(0, key);
	}
	
	public static String encrypt(String input) {
		StringBuilder sb = new StringBuilder(input);
		
		for(int i=0; i<sb.length(); i++) {
			char c = sb.charAt(i);
			int idx = abc.indexOf(c);
			
			if(idx != -1) {
				c = shift.charAt(idx);
				sb.setCharAt(i, c);
			}
		}
		return sb.toString();
	}



	public static void main(String[] args) {
		

	}

}
