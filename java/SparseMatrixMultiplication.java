/*
    Given two sparse matrices A and B, return the result of AB.

    You may assume that A's column number is equal to B's row number.

    Example:

    A = [
      [ 1, 0, 0],
      [-1, 0, 3]
    ]

    B = [
      [ 7, 0, 0 ],
      [ 0, 0, 0 ],
      [ 0, 0, 1 ]
    ]


         |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
    AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                      | 0 0 1 |
 */

// Solution 3: Store non-zero elements in HashMap
// This approach works best if the matrix is sparse (mostly zeros)                
// Time: O(mnl)
// Space: O(mn + nl)
// 08/30/2018

class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        HashMap<Integer, HashMap<Integer, Integer>> tableA = new HashMap<>();
        HashMap<Integer, HashMap<Integer, Integer>> tableB = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] != 0) {
                    tableA.computeIfAbsent(i, x -> new HashMap<>()).put(j, A[i][j]);
                }
            }
        }
        
        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                if (B[i][j] != 0) {
                    tableB.computeIfAbsent(i, x -> new HashMap<>()).put(j, B[i][j]);
                }
            }
        }
        
        int[][] res = new int[A.length][B[0].length];
        for (Integer i : tableA.keySet()) {
            for (Integer j : tableA.get(i).keySet()) {
                if (!tableB.containsKey(j)) {
                    continue;
                }
                for (Integer k : tableB.get(j).keySet()) {
                    res[i][k] += tableA.get(i).get(j) * tableB.get(j).get(k);
                }
            }
        }
        return res;
    }
}

// Solution 2:  early break
// Check for '0', if '0' found, can skip several iterations
// Time: O(mnl) - A m*n  B n*l
// Space: O(1)                   
// 09/27/2017
class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length, n = A[0].length, l = B[0].length;
        int[][] res = new int[m][l];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] != 0) {
                    for (int k = 0; k < l; k++) {
                        if (B[j][k] != 0) {
                            res[i][k] += A[i][j] * B[j][k];
                        }
                    }
                }
            }
        }
        return res;
    }
}

// Solution 1: Brute force solution
// Time limit exceeded
// Time: O(mnl) - A m*n  B n*l
// Space: O(1)
// 09/27/2017

class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        int[][] res = new int[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < A[0].length; k++) {
                    res[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return res;
    }
}