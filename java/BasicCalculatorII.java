/*
	Implement a basic calculator to evaluate a simple expression string.

	The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

	Example 1:
	Input: "3+2*2"
	Output: 7

	Example 2:
	Input: " 3/2 "
	Output: 1

	Example 3:
	Input: " 3+5 / 2 "
	Output: 5

	Note:
	You may assume that the given expression is always valid.
	Do not use the eval built-in library function.
 */

// Solution 2: No Stack
// Use a variable prev to keep track of previous result
// Store result of '*' and '/' in prev,
// add only when '+' and '-'
//
// Time: O(n)
// Space: O(1)
// 08/31/2018
class Solution {
    public int calculate(String s) {
        long res = 0;
        long prev = 0;
        char sign = '+';
        int i = 0;
        while (i < s.length()) {
            while (i < s.length() && s.charAt(i) == ' ') {   // skip white spaces
                i++;
            }
            long cur = 0;
            while (i < s.length() && Character.isDigit(s.charAt(i))) {
                cur = cur * 10 + (s.charAt(i) - '0');
                i++;
            } 
            
            if (sign == '+') {
                res += prev;
                prev = cur;
            } else if (sign == '-') {
                res += prev;
                prev = -cur;
            } else if (sign == '*') {
                prev *= cur;
            } else if (sign == '/') {
                prev /= cur;
            }
            while (i < s.length() && s.charAt(i) == ' ') {  // skip white spaces
                i++;
            }
            if (i < s.length()) {
                sign = s.charAt(i);
                i++;
            }
        }
        return (int) (res + prev);
    }
}


// Solution 1: Stack
// Keep track of sign and number,
// Store intermediate result in a stack
//
// Time: O(n)
// Space: O(n)
// 08/31/2018

class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } 
            
            if (i == s.length() - 1 || c == '+' || c == '-' || c == '*' || c == '/') {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                num = 0;
                sign = c;
            }
        }
        int res = 0;
        for (int n : stack) {
            res += n;
        }
        return res;
    }
}