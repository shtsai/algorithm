/*
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * 
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * 
 * Some examples:
 *   ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 *   ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */

// Solution 1:
// Use stack to store all the numbers.
// When we see an operator, we pop two numbers from the stack,
// and push the result back to the stack.
// Time: O(n) - check every tokens exactly once
// Space: O(n) - push all numbers and results on the stack at most once
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (tokens[i].equals("-")) {
                int subtrahend = stack.pop();
                stack.push(stack.pop() - subtrahend);
            } else if (tokens[i].equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (tokens[i].equals("/")) {
                int divisor = stack.pop();
                stack.push(stack.pop() / divisor);
            } else {    // is a number
                stack.push(Integer.parseInt(tokens[i]));
            }
        }
        return stack.pop();
    }
}