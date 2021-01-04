package section03;

import edu.duke.FileResource;

public class TestCaesarCipher {

	// MAX INDEX = �ִ밪�� ���� �ε��� ã�¾�
	public static int maxIndex(int[] frequent) {
		int maxIdx = 0;

		// �迭���� �ִ밪�� ���� �ε����� ŵ�ϴ� ���
		for (int i = 0; i < frequent.length; i++) {
			if (frequent[maxIdx] < frequent[i])
				maxIdx = i;
		}
		return maxIdx;
	}

	// Ư�� ���ĺ��� ���� ����!! COUNT OCCUR
	public static int[] countLetters(String msg) {
		String alpha = "abcdefghijklmnopqrstuvwxyz";

		// ���ĺ� ��ŭ�� �迭 ����
		int[] counts = new int[26];

		for (int i = 0; i < msg.length(); i++) {
			// msg�� �� ���� �̾Ƽ�
			char ch = Character.toLowerCase(msg.charAt(i));
			// �� ������ �ε����� ã�µ�
			int idx = alpha.indexOf(ch);

			// count�迭�� �� �ε��������� ���� �����������
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
