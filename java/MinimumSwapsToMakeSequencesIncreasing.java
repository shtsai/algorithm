/*
	We have two integer sequences A and B of the same non-zero length.

	We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same index position in their respective sequences.

	At the end of some number of swaps, A and B are both strictly increasing.  (A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)

	Given A and B, return the minimum number of swaps to make both sequences strictly increasing.  It is guaranteed that the given input always makes it possible.

	Example:
	Input: A = [1,3,5,4], B = [1,2,3,7]
	Output: 1
	Explanation: 
	Swap A[3] and B[3].  Then the sequences are:
	A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
	which are both strictly increasing.
	Note:

	A, B are arrays with the same length, and that length will be in the range [1, 1000].
	A[i], B[i] are integer values in the range [0, 2000].
 */

// Solution 1: DP
// Maintain two dp arrays: stay & swap
// which represent the minimum swaps needed if index i is stayed or swaped
// reference: https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/discuss/119835/Java-O(n)-DP-Solution

// Time: O(n)
// Space: O(n)
// 08/15/2018

class Solution {
    public int minSwap(int[] A, int[] B) {
        int[] swap = new int[A.length];
        int[] stay = new int[A.length];
        swap[0] = 1;        // OK to swap first element
        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] >= B[i] || B[i - 1] >= A[i]) {
                swap[i] = swap[i - 1] + 1;
                stay[i] = stay[i - 1];
            } else if (A[i - 1] >= A[i] || B[i - 1] >= B[i]) {
                swap[i] = stay[i - 1] + 1;
                stay[i] = swap[i - 1];
            } else {
                swap[i] = Math.min(stay[i - 1], swap[i - 1]) + 1;
                stay[i] = Math.min(stay[i - 1], swap[i - 1]);
            }
        }
        return Math.min(swap[A.length - 1], stay[A.length - 1]);
    }
}