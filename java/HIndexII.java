/*
 * Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm? 
 */

// Solution 2:
// Binary search
// since the input array is sorted, we can use binary search
class Solution {
    public int hIndex(int[] citations) {
        int start = 0, end = citations.length, len = citations.length;
        while (start < end) {
            int mid = start + (end - start)/2;
            // (len-mid) is the # of papers that have at least citations[mid] citations
            if ((len - mid) == citations[mid]) return len-mid;
            else if ((len - mid) < citations[mid]) end = mid;
            else start = mid+1;
        }
        return len-start;
    }
}

// Solution 1:
// improved version of H-Index
// only need one pass since the input is already sorted
class Solution {
    public int hIndex(int[] citations) {
        int count = 0;
        for (int i = citations.length-1; i >= 0; i--) {
            count++;
            if (count >= citations[i]) return Math.max(citations[i], count-1);
        }
        return count;
    }
}