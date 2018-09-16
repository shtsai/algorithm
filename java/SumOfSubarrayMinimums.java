/*
    Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.

    Since the answer may be large, return the answer modulo 10^9 + 7.

    Example 1:
    Input: [3,1,2,4]
    Output: 17
    Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4]. 
    Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17.
     
    Note:
    1 <= A.length <= 30000
    1 <= A[i] <= 30000
 */

// Solution 2: Left, right + Stack
// reference: https://leetcode.com/problems/sum-of-subarray-minimums/discuss/170750/C++JavaPython-Stack-Solution
// Time: O(n)
// Space: O(n)
// 09/16/2018
class Solution {
    public int sumSubarrayMins(int[] A) {
        int[] left = new int[A.length];
        int[] right = new int[A.length];
        Stack<Integer[]> stack = new Stack<>();
        for (int i = 0; i < A.length; i++) {
            int count = 1;
            while (!stack.isEmpty() && stack.peek()[0] > A[i]) {
                count += stack.pop()[1];
            }
            stack.push(new Integer[] {A[i], count});
            left[i] = count;
        }
        
        stack = new Stack<>();
        for (int i = A.length - 1; i >= 0; i--) {
            int count = 1;
            while (!stack.isEmpty() && stack.peek()[0] >= A[i]) {
                count += stack.pop()[1];
            }
            stack.push(new Integer[] {A[i], count});
            right[i] = count;
        }
        
        int res = 0;
        int mod = 1000000007;
        for (int i = 0; i < A.length; i++) {
            res = (res + (left[i] * right[i] * A[i]) % mod) % mod;
        }
        
        return res;
    }
}    

// Solution 1: Brute force
// Time: O(n ^ 2)
// Space: O(1)
// 09/15/2018

class Solution {
    public int sumSubarrayMins(int[] A) {
        int sum = 0;
        int mod = 1000000007;
        for (int i = 0; i < A.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = i; j < A.length; j++) {
                min = Math.min(min, A[j]);
                sum = (sum + (min % mod)) % mod;
            }
        }
        return sum;
    }
}