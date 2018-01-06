/*
    Implement a basic calculator to evaluate a simple expression string.

    The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

    You may assume that the given expression is always valid.

    Some examples:
    "1 + 1" = 2
    " 2-1 + 2 " = 3
    "(1+(4+5+2)-3)+(6+8)" = 23
    Note: Do not use the eval built-in library function.
 */

// Solution 1: scan + stack
// Scan through the string, then process each char
// Five possibilities:
//   -> digits: add to current number
//   -> '+': add previous number to result, reset number to 0, set sign to 1
//   -> '-': add previous number to result, reset number to 0, set sign to -1
//   -> '(': store result and sign to the stack, reset everything
//   -> ')': update result to be (old result + old sign * new result), reset everything
//
// Time: O(n) - one pass
// Space: O(n) - stack
// 01/06/2018

class Solution {
    public int calculate(String s) {
        char[] chars = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        int result = 0, sign = 1, number = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                number = number * 10 + (chars[i] - '0');
            } else if (chars[i] == '+') {
                result += sign * number;
                sign = 1;
                number = 0;
            } else if (chars[i] == '-') {
                result += sign * number;
                sign = -1;
                number = 0;
            } else if (chars[i] == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (chars[i] == ')') {
                result += sign * number;
                sign = 1;
                number = 0;
                result *= stack.pop();  // get previous sign
                result += stack.pop();  // add to previous result
            }
        }
        if (number != 0) result += sign * number;
        return result;
    }
}