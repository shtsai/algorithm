/*
 * Run Length Encoding
 *
 * example:
 * "ddddwweeeaeeew"   ----->  "d4w2e3a1e3w1"
 *
 * time: O(n), space: O(1)
 *
 */

import java.util.*;

class RunLengthEncoding {
    public static String runLengthEncoding(String s) {
	StringBuilder sb = new StringBuilder();
	int i = 0;
	while (i < s.length()) {
	    sb.append(s.charAt(i));
	    i++;
	    int count = 1;
	    while (i < s.length() && s.charAt(i)==s.charAt(i-1)) {
		i++;
		count++;
	    }
	    sb.append(count);
	}
	return sb.toString();
    }

    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	System.out.println("please enter a string: ");
	String input = scanner.nextLine();
	String res = runLengthEncoding(input);
	System.out.println(res);
    }
}
