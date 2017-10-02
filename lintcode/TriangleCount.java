/*
    Given an array of integers, how many three numbers can be found in the array, so that we can build an triangle whose three edges length is the three numbers that we find?

    Have you met this question in a real interview? Yes
    Example
    Given array S = [3,4,6,7], return 3. They are:

    [3,4,6]
    [3,6,7]
    [4,6,7]
    Given array S = [4,4,4,4], return 4. They are:

    [4(1),4(2),4(3)]
    [4(1),4(2),4(4)]
    [4(1),4(3),4(4)]
    [4(2),4(3),4(4)]
 */

// Solution 1: three pointers
// Use two pointers to locate shortest and middle edges,
// then use another pointer to point at a longest edges (start from the end).
// By triangle property, the sum of any two edges should be greater than the other edge.
// Since the edges are in sorted order, we can guarantee that:
//      1. longest + shortest > middle  (b/c longest > middle)
//      2. longest + middle > shortest  (b/c longest > middle > shortest)
// So we only need to make sure (shortest + middle > longest)
// So we start longest from the last index, if it doesn't satisfy the requirement,
// we decrement l. After we find a valid index for longest edge, all other edges between
// middle and longest will also be valid, so we add (longest - middle) to the count.
//
// Time: O(n^2)
// Space: O(1)
// 10/02/2017
    
public class Solution {
    /*
     * @param S: A list of integers
     * @return: An integer
     */
    public int triangleCount(int[] S) {
        int count = 0;
        Arrays.sort(S);
        for (int s = 0; s < S.length; s++) {
            for (int m = s + 1; m < S.length; m++) {
                int l = S.length-1;
                while (l > m && S[s] + S[m] <= S[l]) {
                    l--;
                } 
                if (l > m) {
                    count += l - m;
                }
            }
        }
        return count;
    }
}