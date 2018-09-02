/*
	We have an array A of non-negative integers.
	For every (contiguous) subarray B = [A[i], A[i+1], ..., A[j]] (with i <= j), we take the bitwise OR of all the elements in B, obtaining a result A[i] | A[i+1] | ... | A[j].
	Return the number of possible results.  (Results that occur more than once are only counted once in the final answer.)

	Example 1:
	Input: [0]
	Output: 1
	Explanation: 
	There is only one possible result: 0.
	
	Example 2:
	Input: [1,1,2]
	Output: 3
	Explanation: 
	The possible subarrays are [1], [1], [2], [1, 1], [1, 2], [1, 1, 2].
	These yield the results 1, 1, 2, 1, 3, 3.
	There are 3 unique values, so the answer is 3.
	
	Example 3:
	Input: [1,2,4]
	Output: 6
	Explanation: 
	The possible results are 1, 2, 3, 4, 6, and 7.
	
	Note:
	1 <= A.length <= 50000
	0 <= A[i] <= 10^9
 */

// Solution 2: Frontier set
// Define B[i][j] = A[i] | A[i + 1] | ... | A[j]
// In each iteration i, HashSet frontier stores B[0][i], B[1][i] ... B[i][i]
// When we handle A[i + 1], we need to update everything in the frontier.
// Then we add A[i + 1] to cur.
//
// This approach is efficient because the values only have 32 bits.
// So in the worst case, there are only 32 values in the frontier set.
// Add the worst case result is 32 * len(A)
//
// Time: O(32n)
// Space: O(32n)
// 09/02/2018
class Solution {
    public int subarrayBitwiseORs(int[] A) {
        HashSet<Integer> res = new HashSet<>();
        HashSet<Integer> frontier = new HashSet<>();
        frontier.add(0);
        for (int a : A) {
            HashSet<Integer> newFrontier = new HashSet<>();
            for (int f : frontier) {
                newFrontier.add(a | f);
            }
            newFrontier.add(a);
            res.addAll(newFrontier);
            frontier = newFrontier;
        }
        return res.size();
    }
}

// Solution 1: Brute force
// Find results for all subarray[i, j]
// 
// Time: O(n ^ 2)
// Space: O(n ^ 2)
// 09/01/2018
class Solution {
    public int subarrayBitwiseORs(int[] A) {
        HashSet<Integer> res = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            int v = 0;
            for (int j = i; j < A.length; j++) {
                v |= A[j];
                res.add(v);
            }
        }
        return res.size();
    }
}