/*
	Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

	Example 1:
	Input: "Let's take LeetCode contest"
	Output: "s'teL ekat edoCteeL tsetnoc"
	Note: In the string, each word is separated by single space and there will not be any extra space in the string.
 */

// Solution 2: Built-in library
// Time: O(n)
// Space: O(n)
// 07/15/2018
class Solution {
    public String reverseWords(String s) {
        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            String r = reverse(word);
            sb.append(r + " ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
    
    private String reverse(String word) {
        StringBuilder sb = new StringBuilder();
        for (int i = word.length() - 1; i >= 0; i--) {
            sb.append(word.charAt(i));
        }
        return sb.toString();
    }
}

// Solution 1:
// Find start and end of each word, then reverse.
// Time: O(n)
// Space: O(n)
// 07/15/2018

class Solution {
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int start = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                reverse(chars, start, i - 1);
                start = i + 1;
            }
        }
        if (start < chars.length) {
            reverse(chars, start, chars.length - 1);
        }
        return new String(chars);
    }
    
    private void reverse(char[] array, int left, int right) {
        while (left < right) {
            char tmp = array[left];
            array[left] = array[right];
            array[right] = tmp;
            left++;
            right--;
        }
    }
}