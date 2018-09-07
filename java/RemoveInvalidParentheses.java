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

// Solution 3: DFS
// Count # of '(' and ')' to remove.
// Then we try to remove '(', and then ')'.
// For continuous occurrences of the same parentheses, we also consider first one.
//
// Time: O(2 ^ n) - In worse case, need to remove, so (n choose n) = 2 ^ n
// Space: O(n ^ 2) - In worse case, n level, each level need O(n) space
// 09/07/2018
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int left = 0, right = 0;
        for (char c : s.toCharArray()) {    // count # of '(' ')' to remove
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left != 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }
        List<String> res = new ArrayList<>();
        dfs(s, res, 0, left, right);
        return new ArrayList<>(res);
    }
    
    // index is the last remove position
    private void dfs(String s, List<String> res, int index, int left, int right) {
        if (left == 0 && right == 0) {
            if (isValid(s)) {
                res.add(s);
            } 
            return;
        } else {
            for (int i = index; i < s.length(); i++) {
                if (i > index && s.charAt(i) == s.charAt(i - 1)) {  // skip duplicates
                    continue;
                }
                if (right > 0) { // remove right parentheses first, because need left to match with right
                    if (s.charAt(i) == ')') {
                        dfs(s.substring(0, i) + s.substring(i + 1), res, i, left, right - 1);
                    }
                } else {
                    if (s.charAt(i) == '(') {
                        dfs(s.substring(0, i) + s.substring(i + 1), res, i, left - 1, right);
                    }
                }
            }
        }
    }
    // Helper function for checking if s is valid           
    private boolean isValid(String s) {
        int stack = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack++;
            } else if (c == ')') {
                if (stack == 0) {
                    return false;
                }
                stack--;
            }
        }
        return stack == 0;
    }
}

// Solution 2: BFS
// Check if current string is valid, if not, remove one of its parenthese and add it to the queue.
// Repeat this process until find valid string, which has the minimum removal.
// Because of the property of BFS, we know the result will contain minimum removal.
//
// Time: O(2 ^ n)
// Space: O(2 ^ n)
// 08/30/2018
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        HashSet<String> seen = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        seen.add(s);
        q.offer(s);
        
        // Stop when find first valid string for minimal removal
        boolean found = false;
        while (!q.isEmpty()) {
            String p = q.poll();
            if (isValid(p)) {
                res.add(p);
                found = true;
            } 
            if (!found) {
                for (int i = 0; i < p.length(); i++) {
                    if (p.charAt(i) != '(' && p.charAt(i) != ')') {
                        continue;
                    }
                    String after = p.substring(0, i) + p.substring(i + 1);
                    if (!seen.contains(after)) {
                        seen.add(after);
                        q.offer(after);
                    }
                }
            }
        }
        return res;
    }
    private boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;
            } 
            if (c == ')') {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }
}

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
// reference: https://discuss.leetcode.com/topic/34875/easy-short-concise-and-fast-java-dfs-3-ms-solution
//
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