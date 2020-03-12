package section02;

import java.util.Arrays;

import section01.Test02_Caesar;

public class Ex03_occurences {

	public static String decrypt(String encrypted) {
		
		/*
		 * count 26 frequencies of A~Z
		 * - find largest value == e라고 가정하자 == e가 가장 많이 출현하는 알파벳이라고 가정하자
		 * - e ~ distance / e의 인덱스는 4
		 * - 26-distance == 암호 해독
		 */
		
		Test02_Caesar cc = new Test02_Caesar();
		
		//암호화된 메세지에서 각 알파벳의 출현 빈도 저장하는 freq
		int[] freq = countOccur(encrypted);
		
		//그중 가장 많이 발생한 알파벳의 인덱스 값을 찾음
		int maxIdx = maxIndex(freq);
		
		//우리가 찾은 e의 위치에서 4를 빼면 a의 위치가 나온다 = 그게 해독을 위한 key!
		int dkey = maxIdx - 4;
		
		if(maxIdx < 4) {
			dkey = 26-(4-maxIdx);
		}

		return cc.encrypt(encrypted, 26-dkey);
	}
	
	//MAX INDEX = 최대값을 가진 인덱스 찾는애
	public static int maxIndex(int[] vals) {
		int maxIdx = 0;
		
		//배열에서 최대값을 가진 인덱스를 킵하는 방식
		for(int i=0; i<vals.length; i++) {
			if(vals[maxIdx] < vals[i])
				maxIdx = i;
		}
		return maxIdx;
	}
	
	//특정 알파벳의 갯수 세기!! COUNT OCCUR
	public static int[] countOccur(String msg) {
		String alpha = "abcdefghijklmnopqrstuvwxyz";
		
		//알파벳 만큼의 배열 생성
		int[] counts = new int[26];
		
		for(int i=0; i<msg.length(); i++) {
			//msg의 각 문자 뽑아서
			char ch = Character.toLowerCase(msg.charAt(i));
			//각 문자의 인덱스를 찾는데
			int idx = alpha.indexOf(ch);
			
			//count배열의 그 인덱스에서도 값을 증가시켜줘라
			if(idx!= -1) counts[idx] ++;
		}
		return counts;
	}
	
	//알파벳 배열로 출력!
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
