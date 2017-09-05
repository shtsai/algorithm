/*
 *  Unique K substring
 *  input: a string and an integer
 *  output: a string array
 *  This program takes a string and return an array containing all substring of length k.
 *  There are no duplicates in the returned array.
 *
 *  time: O(n)
 *  space: O(n)
 */
 


import java.util.*;


class UniqueKSubstring {
    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	
	System.out.println("Please enter a string: ");
	String input = scanner.nextLine();
	String[] res = uniqueKsubstring(input, 2);
	for (String s : res) {
	    System.out.println(s);
	}
    }

    /*  function that finds unique k substring  */
    public static String[] uniqueKsubstring(String s, int k) {
	HashSet<String> set = new HashSet<>();	// use hashset to avoid duplicates
	for (int i = 0; i <= s.length()-k; i++) {
	    set.add(s.substring(i, i+k));
	}
	return set.toArray(new String[set.size()]);
    }
}
