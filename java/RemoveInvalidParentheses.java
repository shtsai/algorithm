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

// Solution 1 version 2: DFS
// First check from left to right.
// Use a counter, +1 for '(', -1 for ')'.
// When the counter falls below zero, we know we have more ')' than '(', so we need to delete '('.
// Start searching for '(' to delete from the last delete index to our last search index
// remove the '(' and recursively call the remover function to continue removing.
// For the recursively calls, they only need to continue searching from the last search index
// and deleting from last delete index.
// Also note that we need to handle duplicates. When multiple ')'s occur, we only handle
// the first one. All subsequent ')'s are ignored.
// After we check from left the right, we also need to check from right to left.
//
// Time: O(n) - at most n chars to delete
// Space: O(1)
// 11/06/2017

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        remover(s, res, 0, 0, false);
        return res;
    }
    
    private void remover(String s, List<String> res, int searchIndex, int lastDelete, boolean rightToLeft) {
        char L = '(';
        char R = ')';
        if (rightToLeft) {
            L = ')';
            R = '(';
        }   
        int counter = 0;
        for (int i = searchIndex; i < s.length(); i++) {
            if (s.charAt(i) == L) {
                counter++;
            } else if (s.charAt(i) == R) {
                counter--;
            }
            
            if (counter < 0) {   // more ')' than '(', need to delete
                for (int j = lastDelete; j <= i; j++) {
                    if (s.charAt(j) == R) {
                        if (j - 1 >= lastDelete && s.charAt(j-1) == R) {
                            continue;
                        }
                        StringBuilder sb = new StringBuilder(s);
                        sb.deleteCharAt(j);
                        remover(sb.toString(), res, i, j, rightToLeft);
                    }
                }
                return;
            }
        }
        if (!rightToLeft) {
            remover(new StringBuilder(s).reverse().toString(), res, 0, 0, true);
        }
        if (counter == 0 && rightToLeft) {
            res.add(new StringBuilder(s).reverse().toString());
        }
    }
}

// Solution 1 version 1:
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