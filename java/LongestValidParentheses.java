/*
 * Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 */

// Solution 2:
// Dynamic programming
// use an array to store the longest valid parentheses ended at index i
// need to be careful with the indices
// reference: https://discuss.leetcode.com/topic/2426/my-dp-o-n-solution-without-using-stack
// time: O(n) one pass
// space: O(n)

class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() <= 1) return 0;
        int[] longest = new int[s.length()];    // stores the longest valid parentheses ended at index i
        int max = Integer.MIN_VALUE;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i-1) == '(') {
                    longest[i] = (i - 2 >= 0) ? longest[i-2] + 2 : 2;
                } else {      // s.charAt(i-1) == ')'
                    if (i - longest[i-1]-1 >= 0 && s.charAt(i-longest[i-1]-1) == '(') {
                        longest[i]=longest[i-1] + 2 + (i-longest[i-1]-2>=0 ? longest[i-longest[i-1]-2] : 0);
                    }
                }
                max = Math.max(max, longest[i]);
            }   // no need to check for '(', b/c it must be 0
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }
}

// Solution 1:
// use stack to elimate valid parentheses,
// the gaps between remaining parentheses are the length of valid parentheses sequence
// time: O(n) - two passes, first elimate, then get max length
// space: O(n) - use a stack
class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {  // push all the non-matching parenthese indices to the stack
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {     // ')' right parenthese
                if (!stack.isEmpty() && s.charAt(stack.peek()) == '(') {      // find a match left parenthese
                    stack.pop();                // remove both parenthese
                } else {
                    stack.push(i);
                }
            }
        }

        int max = Integer.MIN_VALUE;
        int end = s.length();
        while (!stack.isEmpty()) {
            max = Math.max(max, end-stack.peek()-1);
            end = stack.pop();
        }
        return Math.max(max, end);
    }
}
