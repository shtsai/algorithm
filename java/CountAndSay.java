/*
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 *
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth sequence.
 * 
 * Note: The sequence of integers will be represented as a string.
 */

// Solution 1:
// Do n - 1 iterations to generate the string.
// 11/16/2017
class Solution {
    public String countAndSay(int n) {
        String s = "1";
        for (int i = 0; i < n - 1; i++) {
            StringBuilder sb = new StringBuilder();
            int l = 0, r = 0;
            while (r <= s.length()) {
                if (r == s.length() || s.charAt(r) != s.charAt(l)) {
                    int len = r - l;
                    sb.append((len));
                    sb.append(s.charAt(l));
                    l = r;
                }
                r++;
            }
            s = sb.toString();
        }
        return s;
    }
}

// version 1:
public class Solution {
    public String countAndSay(int n) {
        StringBuilder prev;
        StringBuilder cur = new StringBuilder("1");

        for (int i = 1; i < n; i++) { // loop n-1 times to find nth sequence
            prev = cur;            // current sequence becomes previous sequence
            cur = new StringBuilder(); // initialize a new SB
            char say = prev.charAt(0);  // first char
            int count = 1;             // first count;
            
            for (int j = 1; j < prev.length(); j++) {  // iterate over the previous string
                if (prev.charAt(j) == say) {  // find the same char, increase count
                    count++;
                } else {
                    cur.append(count).append(say);  //else, current char has ended, append it and its count to the new string
                    say = prev.charAt(j);     // now handle next char  
                    count = 1;                // initialize its count to 1
                }
            }
            
            cur.append(count).append(say);  // append the last count and say in the current sequence
            
        }
        return cur.toString();
    }
}