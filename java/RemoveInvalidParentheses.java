/*
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 * 
 * Note: The input string may contain letters other than the parentheses ( and ).
 * 
 * Examples:
 * "()())()" -> ["()()()", "(())()"]
 * "(a)())()" -> ["(a)()()", "(a())()"]
 * ")(" -> [""]
 */

// reference: https://discuss.leetcode.com/topic/34875/easy-short-concise-and-fast-java-dfs-3-ms-solution
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        remove(res, s, 0, 0, true);
        return res;
    }
    
    private void remove(List<String> res, String s, int last_invalid, int last_del, boolean leftToRight) {
        int count = 0;      // use to find mismatch
        char[] pair = leftToRight ? new char[]{'(', ')'} : new char[]{')', '('};
  
        for (int i = last_invalid; i < s.length(); i++) {  // start from last invalid
            if (s.charAt(i) == pair[0]) count++;
            if (s.charAt(i) == pair[1]) count--;
            if (count < 0) {    // find an invalid parentheses
                for (int j = last_del; j <= i; j++) { // start from last delete position
                    // we define the invalid parenthese to be pair[1]
                    // the second check ensures that if multiple invalids exist
                    // we only remove the first one to avoid duplicates in the result
                    if (s.charAt(j) == pair[1] && (j == 0 || s.charAt(j-1)!=pair[1])) {
                        String toRemove = s.substring(0, j) + s.substring(j+1, s.length());
                        remove(res, toRemove, i, j, leftToRight);  // recursively solve
                    }
                }
                return;     // stop removing, hand the rest to recursive calls
            }
        }
        // if reach this point, this is not invalid parenthesis in one direction
        // now check the other direction
        if (leftToRight) {  // reverse the string, and check from right to left
            String reversed = new StringBuilder(s).reverse().toString();
            remove(res, reversed, 0, 0, false);
        } else {  // this string has passed the reversed test, add to result
            res.add(new StringBuilder(s).reverse().toString());
        }
    }
}