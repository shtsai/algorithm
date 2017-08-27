/*
 *  Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
 * 
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."
 * 
 * For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.
 * 
 * Note: If there are several possible values for h, the maximum one is taken as the h-index. 
 */

// Solution 2:
// first get the distribution of the citations
// then find the correct H-Index
// reference: https://discuss.leetcode.com/topic/28276/java-o-n-time-with-easy-explanation
class Solution {
    public int hIndex(int[] citations) {
        int len = citations.length;
        // each bucket contains the number of papers that has i citations, 
        // where i is the index of the bucket
        int[] distribution = new int[len+1];
        for (int i = 0; i < len; i++) {
            // distribution[len] stores the # of papers that have more than len citations
            if (citations[i] >= len) distribution[len]++;
            else distribution[citations[i]]++;
        }
        
        // count is the number of papers that have at least i citation
        int count = 0;
        for (int i = len; i >= 0; i--) {
            count += distribution[i];
            if (count >= i) return i;
        }
        return 0;
    }
}

// Solution 1:
// Binary search approach
// use binary search to guess an answer, then scan the array to check if it is correct
// time: O(nlgn)
class Solution {
    public int hIndex(int[] citations) {
        if (citations.length == 0) return citations.length;
        int start = 0, end = citations.length, mid;
        while (start < end) {
            mid = start + (end - start)/2;
            int count = 0;
            // verify guess
            for (int i = 0; i < citations.length; i++) {
                if (citations[i] > mid) {
                    count++;
                }
            }
            if (count == mid) return mid;
            else if (count < mid) {
                end = mid;
            } else {
                start = mid+1;
            }
        }
        return start;
    }
}