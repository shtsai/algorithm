/*
	Given an encoded string, return it's decoded string.

	The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

	You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

	Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

	Examples:
	s = "3[a]2[bc]", return "aaabcbc".
	s = "3[a2[c]]", return "accaccacc".
	s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */

// Solution 2: Two Stacks
// Use one stack for storing previous results,
// and one stack for storing counts.
// There are four possible cases:
//     1. digits: get count and push to count stack
//     2. letters: get all chars and store in cur
//     3. [: push cur to results stack and reset cur to ""
//     4. ]: repeat cur and append to previous result
//
// Time: O(n)
// Space: O(n)
// 09/03/2018
class Solution {
    public String decodeString(String s) {
        Stack<String> results = new Stack<>();
        Stack<Integer> counts = new Stack<>();
        String cur = "";
        int i = 0, len = s.length();
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                int num = 0;
                while (i < len && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                counts.push(num);
            } else if (Character.isLetter(s.charAt(i))) {
                while (i < len && Character.isLetter(s.charAt(i))) {
                    cur += s.charAt(i);
                    i++;
                }
            } else if (s.charAt(i) == '[') {
                results.push(cur);
                cur = "";
                i++;
            } else if (s.charAt(i) == ']') {
                int times = counts.pop();
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < times; j++) {
                    sb.append(cur);
                }
                cur = results.pop() + sb.toString();
                i++;
            }
        }
        return cur;
    }
}

// Solution 1:
// Recursive solve substring [...]
// Time: O(n ^ 2)
// Space: O(n)
// 09/03/2018
class Solution {
    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        int left = 0, right = 0, len = s.length();
        while (left < s.length()) {
            char c = s.charAt(left);
            if (Character.isDigit(c)) {
                while (right < len && s.charAt(right) != '[') {
                    right++;
                }
                int repeat = Integer.parseInt(s.substring(left, right));
                left = right + 1;
                int count = 0;
                while (right < len) {
                    if (s.charAt(right) == '[') {
                        count++;
                    } else if (s.charAt(right) == ']') {
                        count--;
                    }
                    if (count == 0) {
                        break;
                    } else {
                        right++;
                    }
                }
                String sub = decodeString(s.substring(left, right));
                for (int i = 0; i < repeat; i++) {
                    sb.append(sub);
                }
                left = right + 1;
                right = left;
            } else {
                while (right < len && Character.isLetter(s.charAt(right))) {
                    right++;
                }
                sb.append(s.substring(left, right));
                left = right;
            }
        }
        return sb.toString();
    }
}