/*
 * Given an input string, count the total number of palindrome substrings
 * that it contains.
 */
public class CountPalindrome {
	public static int count(String s) {
		int count = s.length();
		for (int i = 1; i < s.length(); i++) {
			int left = i - 1, right = i + 1;
			while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
				count++;
				left--;
				right++;
			}
			left = i - 1;
			right = i;
			while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
				count++;
				left--;
				right++;
			}
		}
		
		return count;
	}
	
	public static void main(String[] args) {
		System.out.println("hellolle" + " -> " + count("hellolle"));
		System.out.println("wowpurerocks" + " -> " + count("wowpurerocks")); 
		System.out.println("ITTTI" + " -> " + count("ITTTI"));
	}

}
