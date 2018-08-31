/*
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 */

// Solution 1:
// Keep track of three variables:
//     left, right, and stack
// Recursively add left and right,
// Break when invalid
//
// Time: O(2^n)
// Space: O(n)
// 08/30/2018
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generate(res, n, n, 0, "");
        return res;
    }
    
    private void generate(List<String> res, int left, int right, int stack, String current) {
        if (left < 0 || right < 0 || stack < 0) {
            return;
        } else if (left == 0 && right == 0 && stack == 0) {
            res.add(current);
            return;
        } else {
            generate(res, left - 1, right, stack + 1, current + "(");
            generate(res, left, right - 1, stack - 1, current + ")");
        }
    }
}

// version 2:
public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        helper("", list, n, n);
        return list;
    }
    
    public void helper(String s, List<String> list, int left, int right) {
        if (left > right) {  // cannot have left > right
            return;
        }
        
        if (left > 0) {     // add (
            helper(s + "(", list, left-1, right);
        }
        if (right > 0) {    // add )
            helper(s + ")", list, left, right-1);
        }
        if (left == 0 && right == 0) {
            list.add(s);
            return;
        }
    }
}