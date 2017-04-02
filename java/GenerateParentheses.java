/*
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 */

// backtracking
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