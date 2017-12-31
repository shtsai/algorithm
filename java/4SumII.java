/*
	Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.

	To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.

	Example:

	Input:
	A = [ 1, 2]
	B = [-2,-1]
	C = [-1, 2]
	D = [ 0, 2]

	Output:
	2

	Explanation:
	The two tuples are:
	1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
	2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */

// Solution 2: Count pairs and HashMap
// Form pairs in (A, B) and (C, D), store the number of pairs for each.
// Then similar to 2SUM, count the total number of pairs of pairs that
// adds up to 0.
// Time: O(n^2)
// Space: O(n^2) - 2 * n^2 pairs
// 2017/12/31

// version 2: don't store pairs for C and D
// count pairs on the fly
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int count = 0;
        HashMap<Integer, Integer> map1 = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                int sum = a + b;
                if (!map1.containsKey(sum)) {
                    map1.put(sum, 1);
                } else {
                    map1.put(sum, map1.get(sum) + 1);
                }
            }
        }
        for (int c : C) {
            for (int d : D) {
                if (map1.containsKey(-c-d)) {
                    count += map1.get(-c-d);
                }
            }
        }
        return count;
    }
}

// version 1: 
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer, Integer> map1 = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                int sum = a + b;
                if (!map1.containsKey(sum)) {
                    map1.put(sum, 1);
                } else {
                    map1.put(sum, map1.get(sum) + 1);
                }
            }
        }
        HashMap<Integer, Integer> map2 = new HashMap<>();
        for (int c : C) {
            for (int d : D) {
                int sum = c + d;
                if (!map2.containsKey(sum)) {
                    map2.put(sum, 1);
                } else {
                    map2.put(sum, map2.get(sum) + 1);
                }
            }
        }
        int count = 0;
        for (Integer x : map1.keySet()) {
            if (map2.containsKey(-x)) {
                count += map1.get(x) * map2.get(-x);
            }
        }
        return count;
    }
}

// Solution 1: Brute force
// Time: O(n^4) - time limit exceeded
// version 2:
// First sort, and break when sum is greater than 0.
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Arrays.sort(A);
        Arrays.sort(B);
        Arrays.sort(C);
        Arrays.sort(D);
        int count = 0;
        for (int a : A) {
            for (int b : B) {
                for (int c : C) {
                    for (int d : D) {
                        if (a + b + c + d == 0) count++;
                        if (a + b + c + d > 0) break;
                    }
                }
            }
        }
        return count;
    }
}

// version 1:
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int count = 0;
        for (int a : A) {
            for (int b : B) {
                for (int c : C) {
                    for (int d : D) {
                        if (a + b + c + d == 0) count++;
                    }
                }
            }
        }
        return count;
    }
}