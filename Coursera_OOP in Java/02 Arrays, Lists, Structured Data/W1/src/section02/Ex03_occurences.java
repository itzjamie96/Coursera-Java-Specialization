package section02;

import java.util.Arrays;

import section01.Test02_Caesar;

public class Ex03_occurences {

	public static String decrypt(String encrypted) {
		
		/*
		 * count 26 frequencies of A~Z
		 * - find largest value == e��� �������� == e�� ���� ���� �����ϴ� ���ĺ��̶�� ��������
		 * - e ~ distance / e�� �ε����� 4
		 * - 26-distance == ��ȣ �ص�
		 */
		
		Test02_Caesar cc = new Test02_Caesar();
		
		//��ȣȭ�� �޼������� �� ���ĺ��� ���� �� �����ϴ� freq
		int[] freq = countOccur(encrypted);
		
		//���� ���� ���� �߻��� ���ĺ��� �ε��� ���� ã��
		int maxIdx = maxIndex(freq);
		
		//�츮�� ã�� e�� ��ġ���� 4�� ���� a�� ��ġ�� ���´� = �װ� �ص��� ���� key!
		int dkey = maxIdx - 4;
		
		if(maxIdx < 4) {
			dkey = 26-(4-maxIdx);
		}

		return cc.encrypt(encrypted, 26-dkey);
	}
	
	//MAX INDEX = �ִ밪�� ���� �ε��� ã�¾�
	public static int maxIndex(int[] vals) {
		int maxIdx = 0;
		
		//�迭���� �ִ밪�� ���� �ε����� ŵ�ϴ� ���
		for(int i=0; i<vals.length; i++) {
			if(vals[maxIdx] < vals[i])
				maxIdx = i;
		}
		return maxIdx;
	}
	
	//Ư�� ���ĺ��� ���� ����!! COUNT OCCUR
	public static int[] countOccur(String msg) {
		String alpha = "abcdefghijklmnopqrstuvwxyz";
		
		//���ĺ� ��ŭ�� �迭 ����
		int[] counts = new int[26];
		
		for(int i=0; i<msg.length(); i++) {
			//msg�� �� ���� �̾Ƽ�
			char ch = Character.toLowerCase(msg.charAt(i));
			//�� ������ �ε����� ã�µ�
			int idx = alpha.indexOf(ch);
			
			//count�迭�� �� �ε��������� ���� �����������
			if(idx!= -1) counts[idx] ++;
		}
		return counts;
	}
	
	//���ĺ� �迭�� ���!
	public static char[] printAlpha() {
		char[] alpha = new char[26];
		for(int i=0; i<alpha.length; i++) {
			alpha[i] = (char)('A'+i);
		}
		return alpha;
	}
	
	public static void main(String[] args) {
		
		System.out.println(Arrays.toString(printAlpha()));
		
//		int[] counts = countOccur("hello");
//		System.out.println(Arrays.toString(counts));
		
		System.out.println(decrypt("IUHH"));
		
	}

}
