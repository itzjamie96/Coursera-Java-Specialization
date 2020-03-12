package string03_Debugging;

public class Debugging {

	public static void findAbc(String input) {

		System.out.println("input length: "+input.length());
		
		int index = input.indexOf("abc");
		
		System.out.println("start index: "+index);
		
		while (true) {
			if (index == -1 || index >= input.length() - 3) {
				break;
			}
			
			System.out.println("\nindex b4 found: "+index);
			
			String found = input.substring(index + 1, index + 4);
			System.out.println("found: "+found);
			index = input.indexOf("abc", index + 3);

			System.out.println("updated index: "+index);
		}
	}

	public static void test() {

	}

	public static void main(String[] args) {
//		 findAbc("abcd");
		findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
//		findAbc("abcabcabcabca");
		
/*
“ttabcesoeiabco”


“abcbabccabcd”


“qwertyabcuioabcp”


“abcabcabcabca”
*/

	}

}
