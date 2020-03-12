package section01;

import edu.duke.FileResource;

public class Test02_Caesar {

	public static String encrypt(String input, int key) {
		

		/* Caesar Cipher
		 * 1. 알파벳 준비
		 * 2. 바뀐 알파벳 준비
		 * 3. 기존 알파벳에서의 인덱스 킵
		 * 4. 킵한 인덱스 값을 바뀐거에서 확인
		 * 5. 바뀐 값으로 input 바꾸기
		 */
		
		StringBuilder sb = new StringBuilder(input);
		
		//기존 알파벳과 바뀐 알파벳
		String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String shift = abc.substring(key) + abc.substring(0, key);
		
		//기존 알파벳에서의 인덱스 킵
		for (int i=0; i<sb.length(); i++) {

			//먼저 현재 어떤 알파벳인지 알아내야함
			char curr = sb.charAt(i);
			int idx = abc.indexOf(String.valueOf(curr).toUpperCase());
			
			//바뀐 알파벳에 해당되는 인덱스값으로 찾기
			if(idx != -1) {
				char newChar = 0;

				if(Character.isLowerCase(curr)) {
					newChar = Character.toLowerCase(shift.charAt(idx));
					sb.setCharAt(i, newChar);					
				} else {
					newChar = shift.charAt(idx);
					sb.setCharAt(i, newChar);					
				}	
			}
		}

		return sb.toString();
	}
	
	//ENCRYPT TWO KEYS
	public static String encryptTwoKeys(String input, int k1, int k2) {
		
		StringBuilder sb = new StringBuilder(input);
		StringBuilder ans = new StringBuilder();
		
		for (int i=0; i<sb.length(); i++) {
			
			//k1 = 짝수 / k2 = 홀수 인덱스
			if(i%2==0) {
//				String a = encrypt(String.valueOf(sb.charAt(i)), k1);
				ans.append(encrypt(String.valueOf(sb.charAt(i)), k1));
			} else {
//				String b = encrypt(String.valueOf(sb.charAt(i)), k2);	
				ans.append(encrypt(String.valueOf(sb.charAt(i)), k2));
//				System.out.print(b);
			}
		}
		
		return ans.toString();
	}
	
	//TEST TWO
	public static void testTwo() {
		String msg = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
		
		int k1 = 8;
		int k2 = 21;
		String encrypted = encryptTwoKeys(msg, k1, k2);
		System.out.println(encrypted);
	}
	
	
	//TEST CAESAR
	public static void testCaesar() {
//		FileResource fr = new FileResource();
//		String msg = fr.asString();
		
		String msg = "FREE";
		
		int key = 3;
		String encrypted = encrypt(msg, key);
		
		System.out.println("key is "+key + "\n" + encrypted);
	}
	
	public static void main(String[] args) {
		testCaesar();
//		testTwo();
	}

}
