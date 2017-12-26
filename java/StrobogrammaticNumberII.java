/*
    A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

    Find all strobogrammatic numbers that are of length = n.

    For example,
    Given n = 2, return ["11","69","88","96"].
 */

// Solution 2: Recursive solution (by length)
// Use helper functions to recursively solve the same problem with smaller length.
// Base cases:
//      0: emtpy string
//      1: 0, 1, 8
// After getting the result of smaller subproblem,
// expand the string to longer strobogrammatic numbers.
// Need to check if n == max, if so, we are
// at the top level of the function call, and we cannot add "00".
// Time: O(5^n)
// Space: O(n) - smaller call stack
// 10/07/2017

class Solution {
    public List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }
    
    private List<String> helper(int n, int max) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            res.add("");
            return res;
        }
        if (n == 1) {
            res.add("0");
            res.add("1");
            res.add("8");
            return res;
        }
        
        List<String> sublist = helper(n-2, max);
        
        for (String s : sublist) {
            if (n != max) {
                res.add("0" + s + "0");
            }
            res.add("1" + s + "1");
            res.add("8" + s + "8");
            res.add("6" + s + "9");
            res.add("9" + s + "6");
        }
        return res;
    }
}

// Solution 2: Recursively find each number
// Can cause more function call than Solution 3
// Time: O(5^n) - n level in total, 5 branches at each level
// Space: O(5^n)
// 12/26/2017
class Solution {
    final String[] center = {"0", "1", "8"};
    final String[][] pairs = {{"1", "1"},
                             {"6", "9"},
                             {"8", "8"},
                             {"9", "6"}};
    
    public List<String> findStrobogrammatic(int n) {
        List<String> res = new ArrayList<>();
        find(res, n, "");
        return res;
    }
    
    public void find(List<String> res, int len, String cur) {
        if (cur.length() == len) {
            res.add(cur);
            return;
        }
        if (cur.length() == 0 && (len % 2 == 1)) {
            for (String c : center) {
                find(res, len, c);
            }
            return;
        }
        for (String[] p : pairs) {
            find(res, len, p[0] + cur + p[1]);
        }
        if (len - cur.length() != 2) {
            find(res, len, "0" + cur + "0");
        }
    }
}

// Solution 1: iterative solution
// Initialize list for base cases (0, 1, 2).
// Odd and even n have difference results, need to handle that.
// Build the result from small cases to n by adding valid pairs
// to the two sides of strings.
// Need to handle "00" specially, because cannot start a number with 0.
// Only add "00" when n > 2.
// Time: O(5^n)
// Space: O(5^n)
// 10/07/2017

class Solution {
    public List<String> findStrobogrammatic(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        
        if (n % 2 == 1) {
            res.add("0");
            res.add("1");
            res.add("8");
            n--;
        } else {
            res.add("11");
            res.add("88");
            res.add("69");
            res.add("96");
            if (n == 2) return res;
            res.add("00");
            n -= 2;
        }
        
        String[][] maps = new String[][] {{"1","1"},{"8","8"},{"6","9"},{"9","6"}};
        
        while (n > 0) {
            List<String> newRes = new ArrayList<>();
            for (String s : res) {
                for (String[] map : maps) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(map[0]);
                    sb.append(s);
                    sb.append(map[1]);
                    newRes.add(sb.toString());
                }
                if (n > 2) {  // special case for adding two "0"s
                    StringBuilder sb = new StringBuilder();
                    sb.append("0");
                    sb.append(s);
                    sb.append("0");
                    newRes.add(sb.toString());
                }
            }
            res = newRes;
            n -= 2;
        }
        return res;
    }
}