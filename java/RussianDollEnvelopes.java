/*
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
 * 
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 * 
 * Example:
 * Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */

// dynamic program and binary search
// 1. Sort the array by ascending order on width and descending order on height if width are same.
//    When widths are the same, we need heights to descending in order to break the increasing subsequence.
// 2. Since the widths are in ascending order, now find the longest increasing subsequence based on height.
// Related problem: Longest Increasing Subsequence

public class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;
        Arrays.sort(envelopes, new Cmp());
        int[] dp = new int[envelopes.length];
        
        int size = 0;
        for (int[] envelope : envelopes) {
            int i = 0, j = size;
            while (i != j) {
                int mid = i + (j - i) / 2;
                if (envelope[1] > dp[mid]) {
                    i = mid + 1;
                } else {
                    j = mid;
                }
            }
            dp[i] = envelope[1];
            if (i == size) size++;
        }
        
        return size;
    }
    
    private class Cmp implements Comparator<int[]> {    // sort by accending width,
        public int compare(int[] a, int[] b) {          // descending height
            if (a[0] == b[0]) return b[1] - a[1];
            else return a[0] - b[0];
        }
    }
}