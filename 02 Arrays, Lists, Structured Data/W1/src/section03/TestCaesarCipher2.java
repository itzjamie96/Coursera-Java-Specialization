package section03;

import edu.duke.FileResource;

public class TestCaesarCipher2 {
	
	//HALF SPLIT STRING
	public static String halfOfString(String msg, int start) {
		
		String split = "";
		
		for(int i=start; i<msg.length();i+=2) {
			split += msg.charAt(i);
		}
		
		return split;
	}
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
	
	public static int getKey(String input) {
		int[] freq = countLetters(input);
		
		int maxIdx = maxIndex(freq);
		
		int dkey = maxIdx - 4;
		
		if(maxIdx < 4) {
			dkey = 26-(4-maxIdx);
		}		

		return dkey;
	}
	
	public static String breakCaesarCipher(String input) {
		
		String half1 = halfOfString(input, 0);
		String half2 = halfOfString(input, 1);
		
		int k1 = getKey(half1);
		int k2 = getKey(half2);
		
		System.out.println(k1+"/"+k2);
		
		CaesarCipher2 cc = new CaesarCipher2(k1, k2);

		return cc.decrypt(input);

	}
	
	public static void simpleTest() {
		FileResource fr = new FileResource();
		String file = fr.asString();
		
//		String file = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
		
		CaesarCipher2 cc = new CaesarCipher2(14, 24);

//		System.out.println(cc.decrypt("Hfs cpwewloj loks cd Hoto kyg Cyy."));

//		System.out.println(cc.encrypt(file));
//		System.out.println(cc.decrypt(cc.encrypt(file)));
		
		
		
		String test = breakCaesarCipher(file);
		System.out.println(test);
		

	}
	public static void main(String[] args) {
		simpleTest();

	}

}
