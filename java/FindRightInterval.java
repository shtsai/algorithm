/*
    Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i, which can be called that j is on the "right" of i.

    For any interval i, you need to store the minimum interval j's index, which means that the interval j has the minimum start point to build the "right" relationship for interval i. If the interval j doesn't exist, store -1 for the interval i. Finally, you need output the stored value of each interval as an array.

    Note:
    You may assume the interval's end point is always bigger than its start point.
    You may assume none of these intervals have the same start point.
    Example 1:
    Input: [ [1,2] ]

    Output: [-1]

    Explanation: There is only one interval in the collection, so it outputs -1.
    Example 2:
    Input: [ [3,4], [2,3], [1,2] ]

    Output: [-1, 0, 1]

    Explanation: There is no satisfied "right" interval for [3,4].
    For [2,3], the interval [3,4] has minimum-"right" start point;
    For [1,2], the interval [2,3] has minimum-"right" start point.
    Example 3:
    Input: [ [1,4], [2,3], [3,4] ]

    Output: [-1, 2, -1]

    Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
    For [2,3], the interval [3,4] has minimum-"right" start point.
 */

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

// Solution 2: Treemap
// key - start time, value - original index
// use ceilingEntry(target) in TreeMap to find the minimum entry whose
// key value is >= to target.
//
// Time: O(nlgn)
// Space: O(n)
// 01/24/2018

class Solution {
    public int[] findRightInterval(Interval[] intervals) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < intervals.length; i++) {
            map.put(intervals[i].start, i);
        }
        int[] res = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            Map.Entry<Integer, Integer> e = map.ceilingEntry(intervals[i].end);
            res[i] = e == null ? -1 : e.getValue();
        }
        return res;
    }
}

// Solution 1: Sorting + Binary Search
// Store mapping from start to original index.
// Then sort the intervals by start time.
// Then do binary search to find next "right" interval.
// Time: O(nlgn)
// Space: O(n) - create a copy and mapping
// 01/24/2018

class Solution {
    public int[] findRightInterval(Interval[] intervals) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Interval[] copy = new Interval[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            map.put(intervals[i].start, i);
            copy[i] = intervals[i];
        }
        
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        
        int[] res = new int[intervals.length];
        for (int i = 0; i < copy.length; i++) {
            Interval tmp = binarySearch(intervals, copy[i].end);
            if (tmp == null) {
                res[i] = -1;
            } else {
                res[i] = map.get(tmp.start);
            }
        }
        return res;
    }
    
    public Interval binarySearch(Interval[] intervals, int target) {
        int left = 0, right = intervals.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (intervals[mid].start < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (intervals[left].start >= target) {
            return intervals[left];
        } else {
            return null;
        }
    }
}