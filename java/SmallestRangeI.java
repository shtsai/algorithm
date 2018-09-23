/*
	Given an array A of integers, for each integer A[i] we may choose any x with -K <= x <= K, and add x to A[i].

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
	Output: 0
	Explanation: B = [3,3,3] or B = [4,4,4]

	Note:
	1 <= A.length <= 10000
	0 <= A[i] <= 10000
	0 <= K <= 10000
 */

// Solution 2: Min and Max
// Find the min and max in A.
// Then the min and max in B will be (min + k) and (max - k)
//
// Time: O(n)
// Space: O(1)
// 09/23/2018
class Solution {
    public int smallestRangeI(int[] A, int K) {
        int min = A[0];
        int max = A[0];
        for (int a : A) {
            min = Math.min(min, a);
            max = Math.max(max, a);
        }
        int diff = (max - K) - (min + K);
        if (diff < 0) {
            return 0;
        } else {
            return diff;
        }
    }
}

// Solution 1: Sort + Create Interval
// Sort and create intervals
// Then compare first and last interval
// Time: O(nlogn)
// Space: O(n)
// 09/22/2018

class Solution {
    class Interval {
        int start;
        int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public int smallestRangeI(int[] A, int K) {
        Arrays.sort(A);
        List<Interval> intervals = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            intervals.add(new Interval(A[i] - K, A[i] + K));
        }
        if (intervals.size() <= 1 || intervals.get(intervals.size() - 1).start <= intervals.get(0).end) {
            return 0;
        } 
        return intervals.get(intervals.size() - 1).start - intervals.get(0).end;
    }
}