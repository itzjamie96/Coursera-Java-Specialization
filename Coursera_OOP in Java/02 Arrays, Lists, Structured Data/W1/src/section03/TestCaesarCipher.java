package section03;

import edu.duke.FileResource;

public class TestCaesarCipher {

	// MAX INDEX = 최대값을 가진 인덱스 찾는애
	public static int maxIndex(int[] frequent) {
		int maxIdx = 0;

		// 배열에서 최대값을 가진 인덱스를 킵하는 방식
		for (int i = 0; i < frequent.length; i++) {
			if (frequent[maxIdx] < frequent[i])
				maxIdx = i;
		}
		return maxIdx;
	}

	// 특정 알파벳의 갯수 세기!! COUNT OCCUR
	public static int[] countLetters(String msg) {
		String alpha = "abcdefghijklmnopqrstuvwxyz";

		// 알파벳 만큼의 배열 생성
		int[] counts = new int[26];

		for (int i = 0; i < msg.length(); i++) {
			// msg의 각 문자 뽑아서
			char ch = Character.toLowerCase(msg.charAt(i));
			// 각 문자의 인덱스를 찾는데
			int idx = alpha.indexOf(ch);

			// count배열의 그 인덱스에서도 값을 증가시켜줘라
			if (idx != -1)
				counts[idx]++;
		}
		return counts;
	}

	//THIS USES 'E'
	public static String breakCaesarCipher(String input) {
		int[] freq = countLetters(input);
		
		int maxIdx = maxIndex(freq);
		
		int dkey = maxIdx - 4;
		System.out.println(dkey);
		
		if(maxIdx < 4) {
			dkey = 26-(4-maxIdx);
		}

		CaesarCipher cc = new CaesarCipher(dkey);
		return cc.decrypt(input);
	}
	
	public static void simpleTest() {
//		FileResource fr = new FileResource();
//		String file = fr.asString();
		
		String file = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
		
		CaesarCipher cc = new CaesarCipher(15);
		String encrypted = cc.encrypt(file);
		System.out.println(encrypted);
		
		String decrypted = cc.decrypt(encrypted);
		System.out.println("decrypted? "+decrypted);
		
		String test = breakCaesarCipher(file);
		System.out.println(test);
		
	}
	
	public static void main(String[] args) {
		simpleTest();

	}

}
