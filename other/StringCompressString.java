/*
 * Similar to LC443, StringCompression.java
 * Given a string, compress it.
 * For example, "aabbccc" -> "a2b2c3"
 * Return compressed string.
 */

// Solution 1: Two pointers + StringBuilder
// Define a block to be a continuous range of the same number.
// Use a pointer current to point at the first char of the current block.
// Use a pointer i to scan through the string.
// When i reaches the end of a block, we append the first char to the result,
// and then append the length of this block to the result.
// Move current to the first char of the next block, and repeat.
//
// Time: O(n) - one pass
// Space: O(1)
// 11/08/2017

import java.util.*;
class StringCompressString {
    public static String compress(String s) {
	StringBuilder sb = new StringBuilder();
	int current = 0;
	for (int i = 0; i < s.length(); i++) {
	    if (i + 1 == s.length() || s.charAt(i) != s.charAt(i+1)) { // i is the end of a block
		sb.append(s.charAt(current));
		if (i > current) {  // count > 1
		    sb.append(i - current + 1);
		}
		current = i+1;
	    }
	}
	return sb.toString();
    }

    public static void main(String[] args) {
	String s1 = "aabbccc";
	String s2 = "abc";
	String s3 = "aaaaaabbbbbbbbbbbb";
	System.out.println(s1 + " --> " + compress(s1));
	System.out.println(s2 + " --> " + compress(s2));
	System.out.println(s3 + " --> " + compress(s3));
    }
}
