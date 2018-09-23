/*
    Given an array A of integers, for each integer A[i] we need to choose either x = -K or x = K, and add x to A[i] (only once).

    After this process, we have some array B.

    Return the smallest possible difference between the maximum value of B and the minimum value of B.

    Example 1:
    Input: A = [1], K = 0
    Output: 0
    Explanation: B = [1]
    
    Example 2:
    Input: A = [0,10], K = 2
    Output: 6
    Explanation: B = [2,8]
   
    Example 3:
    Input: A = [1,3,6], K = 3
    Output: 3
    Explanation: B = [4,6,3]
     
    Note:
    1 <= A.length <= 10000
    0 <= A[i] <= 10000
    0 <= K <= 10000
 */

// Solution 2: Sort + Linear scan
// reference: https://leetcode.com/problems/smallest-range-ii/solution/
// Time: O(nlogn)
// Space: O(1)
// 09/23/2018
class Solution {
    public int smallestRangeII(int[] A, int K) {
        Arrays.sort(A);
        int res = A[A.length - 1] - A[0];
        for (int i = 0; i < A.length - 1; i++) {
            int a = A[i];
            int b = A[i + 1];
            int high = Math.max(A[A.length - 1] - K, a + K);
            int low = Math.min(A[0] + K, b - K);
            res = Math.min(res, high - low);
        }
        return res;
    }
}

// Solution 1: Brute force
// Try all combination
// Time: O(2 ^ n)
// Space: O(n)
// 09/22/2018

class Solution {
    public int smallestRangeII(int[] A, int K) {
        List<Integer> cur = new ArrayList<>();
        int[] memo = new int[A.length];
        int res = search(A, K, cur, 0, memo, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return res;
    }
    
    private int search(int[] A, int K, List<Integer> cur, int index, int[] memo, int max, int min) {
        if (index == A.length) {
            return max - min;
        } else if (memo[index] != 0) {
            return memo[index];
        }
        int res = Integer.MAX_VALUE;
        cur.add(A[index] + K);
        res = Math.min(res, search(A, K, cur, index + 1, memo, Math.max(max, A[index] + K), Math.min(min, A[index] + K)));
        cur.remove(cur.size() - 1);
        
        cur.add(A[index] - K);
        res = Math.min(res, search(A, K, cur, index + 1, memo, Math.max(max, A[index] - K), Math.min(min, A[index] - K)));
        cur.remove(cur.size() - 1);
        return res;
    }
}