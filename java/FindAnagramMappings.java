/*
	Given two lists Aand B, and B is an anagram of A. B is an anagram of A means B is made by randomizing the order of the elements in A.

	We want to find an index mapping P, from A to B. A mapping P[i] = j means the ith element in A appears in B at index j.

	These lists A and B may contain duplicates. If there are multiple answers, output any of them.

	For example, given

	A = [12, 28, 46, 32, 50]
	B = [50, 12, 32, 46, 28]
	We should return
	[1, 4, 3, 2, 0]
	as P[0] = 1 because the 0th element of A appears at B[1], and P[1] = 4 because the 1st element of A appears at B[4], and so on.
	Note:

	A, B have equal lengths in range [1, 100].
	A[i], B[i] are integers in range [0, 10^5].
 */

// Solution 1: Use HashMap<Integer, Queue<Integer>>
// Store the indices of each value in B.
// Then use this HashMap to assign indices to res array.
// Time: O(n) 
// Space: O(n)
// 01/10/2018

class Solution {
    public int[] anagramMappings(int[] A, int[] B) {
        HashMap<Integer, Queue<Integer>> map = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            int value = B[i];
            if (!map.containsKey(value)) {
                map.put(value, new LinkedList<>());
            }
            map.get(value).offer(i);
        }
        
        int[] res = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            int value = A[i];
            res[i] = map.get(value).poll();
        }
        return res;
    }
}