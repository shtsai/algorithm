/*
	Implement a basic calculator to evaluate a simple expression string.

	The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

	The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.

	You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].

	Some examples:
	"1 + 1" = 2
	" 6-4 / 2 " = 4
	"2*(5+5*2)/3+(6/2+8)" = 21
	"(2+6* 3+5- (3*14/7+2)*5)+3"=-12

	Note: Do not use the eval built-in library function.
 */

// Solution 1: Recursion + Stack + Basic calculator II
// When see '(', find corresponding ')' and recursively solve small subproblem.
// Use stack to store results like in Basic calculator II.
// 
// Time: O(n)
// Space: O(n)
// 08/31/2018

class Solution {
    public int calculate(String s) {
        s = s.replaceAll("\\s+", "");
        char[] chars = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int i = 0;
        while (i < chars.length) {
            if (chars[i] == '(') {
                int j = i + 1;
                int count = 1;
                while (j < chars.length && count > 0) {
                    if (chars[j] == '(') {
                        count++;
                    } else if (chars[j] == ')') {
                        count--;
                    }
                    j++;
                }
                int blockValue = calculate(s.substring(i + 1, j - 1));
                i = j;
                if (sign == '+') {
                    stack.push(blockValue);
                } else if (sign == '-') {
                    stack.push(-blockValue);
                } else if (sign == '*') {
                    stack.push(stack.pop() * blockValue);
                } else if (sign == '/') {
                    stack.push(stack.pop() / blockValue);
                }
            } else if (chars[i] >= '0' && chars[i] <= '9') {
                int num = 0;
                while (i < chars.length && chars[i] >= '0' && chars[i] <= '9') {
                    num = num * 10 + (chars[i] - '0');
                    i++;
                }
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
            } else {
                sign = chars[i];
                i++;
            }
        }
        int result = 0;
        for (int n : stack) {
            result += n;
        }
        return result;
    }
}