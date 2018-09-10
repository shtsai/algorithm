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
// 09/09/2018
class Solution {
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        int max = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i - 2 >= 0 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0) + dp[i - 1] + 2;
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
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
