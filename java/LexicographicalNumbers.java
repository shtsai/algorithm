/* 
 * Given an integer n, return 1 - n in lexicographical order.
 * 
 * For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].
 * 
 * Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000. 
 */

// Solution 1:
// First go up to highest level possible: 1 -> 10 -> 100
// Then increase by one at every step: 100 -> 101 -> 102 ... -> 109
// Go down one level and continue: 109 -> 11 -> 110 -> 111
//
// reference: https://discuss.leetcode.com/topic/55184/java-o-n-time-o-1-space-iterative-solution-130ms
// Time: O(n), Space: O(1)

class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        int i = 1;
        for (int j = 0; j < n; j++) {
            res.add(i);
            if (i * 10 <= n) {    // muliply by 10 when doesn't exceed limit
                i *= 10;
            } else if (i % 10 != 9 && i + 1 <= n) { // when last digit is not 9 and within limit
                i++;           
            } else {  // at this point, i cannot grow, need to shrink
                while (i / 10 % 10 == 9) {  // multiple 9s at the end, shrink to smallest possible form
                    i /= 10;
                }
                i = i / 10 + 1;   // add 1 to start next level
            }
        }
        return res;
    }
}