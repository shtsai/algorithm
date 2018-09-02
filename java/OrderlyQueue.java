/*
	A string S of lowercase letters is given.  Then, we may make any number of moves.
	In each move, we choose one of the first K letters (starting from the left), remove it, and place it at the end of the string.
	Return the lexicographically smallest string we could have after any number of moves.

	Example 1:
	Input: S = "cba", K = 1
	Output: "acb"
	Explanation: 
	In the first move, we move the 1st character ("c") to the end, obtaining the string "bac".
	In the second move, we move the 1st character ("b") to the end, obtaining the final result "acb".
	
	Example 2:
	Input: S = "baaca", K = 3
	Output: "aaabc"
	Explanation: 
	In the first move, we move the 1st character ("b") to the end, obtaining the string "aacab".
	In the second move, we move the 3rd character ("c") to the end, obtaining the final result "aaabc".
	 
	Note:
	1 <= K <= S.length <= 1000
	S consists of lowercase letters only.
 */

// Solution 2: Math
// When K == 1, we can only rotate the String to find minimum.
// When K >= 2, we can get any permutation. 
// So we can just sort the array and return min permutation.
//
// Time: O(n ^ 2)
// Space: O(n ^ 2)
// 09/02/2018
class Solution {
    public String orderlyQueue(String S, int K) {
        if (K == 1) {
            String min = S;
            for (int i = 0; i < S.length(); i++) {
                String newS = S.substring(1) + S.substring(0, 1);
                if (newS.compareTo(min) < 0) {
                    min = newS;
                }
                S = newS;
            }
            return min;
        } else {
            char[] chars = S.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        }
    }
}

// Solution 1: Brute force
// Search all possible moves.
// Time: O(k ^ n)
// Space: O(n!)
// 09/01/2018

class Solution {
    String min;
    public String orderlyQueue(String S, int K) {
        min = S;
        HashSet<String> seen = new HashSet<>();
        search(S, K, seen);
        return min;
    }
    
    private void search(String S, int K, HashSet<String> seen) {
        if (seen.contains(S)) {
            return;
        }
        seen.add(S);
        if (S.compareTo(min) < 0) {
            min = S;
        }
        for (int i = 0; i < K; i++) {
            String newS = S.substring(0, i) + S.substring(i + 1) + S.substring(i, i + 1);
            search(newS, K, seen);
        }
    }
}