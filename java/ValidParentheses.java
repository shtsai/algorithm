/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 */


// Solution 1 version 3:
// More generic, only need to modify map to add or remove new type of parenthesis
// 08/03/2018
class Solution {
    public boolean isValid(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (!map.containsKey(c)) {
                stack.push(c);
            } else if (stack.isEmpty() || stack.pop() != map.get(c)) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
// Solution 1 version 2:
// use HashMap
class Solution {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return true;
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {     // left brackets
                stack.push(c);
            } else {        // right brackets
                if (stack.isEmpty() || map.get(stack.pop()) != c) return false;  // left and right doesn't match
            }
        }
        return stack.isEmpty();
    }
}


// Solution 1:
// use stack to store the left parentheses that we have seen
// when see a right parentheses, pop the last left parenthese off the stack to see if it matches the right one
public class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (!stack.isEmpty()){
                char pop = stack.pop();
                if ((pop == '(' && c != ')') || (pop == '[' && c != ']') || (pop == '{' && c != '}')) {
                    return false;
                }
            } else {
                // try to insert to right brackets to an empty stack, false
                return false;
            }
        }
        return stack.isEmpty();
    }
}