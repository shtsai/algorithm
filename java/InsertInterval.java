/*
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * 
 * You may assume that the intervals were initially sorted according to their start times.
 * 
 * Example 1:
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 * 
 * Example 2:
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 * 
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
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

// Solution 3: 
// First add all non-overlapping intervals.
// Then merge all overlapping intervals and add to result.
// Finally, add remaining intervals.
// Time: O(n)
// Space: O(1)
// 01/05/2018
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        int i = 0;
        while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
            res.add(intervals.get(i));
            i++;
        }
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            i++;
        }
        res.add(newInterval);
        while (i < intervals.size()) {
            res.add(intervals.get(i));
            i++;
        }
        return res;
    }
}

// Solution 2:
// iterative merge
// look for a gap where the new interval can be inserted
// when encounter a overlapped interval, delete it from the list, and update the new interval (by merging the two)
// when the interval is still not inserted after a round of searching, it must be added to the end of the list
// Time: O(n)
// Space: O(1)

// version 2: add to a new list
// 01/05/2018
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        boolean added = false;
        for (Interval i : intervals) {
            if (!added) {
                if (i.end < newInterval.start) {
                    res.add(i);
                } else if (i.start > newInterval.end) {
                    res.add(newInterval);
                    res.add(i);
                    added = true;
                } else {
                    newInterval.start = Math.min(newInterval.start, i.start);
                    newInterval.end = Math.max(newInterval.end, i.end);
                } 
            } else {
                res.add(i);
            }
        }
        if (!added) {
            res.add(newInterval);
        }
        return res;
    }
}

// version 1:
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        boolean inserted = false;  
        int prev_end = -1, i = 0;
        while (i < intervals.size()) {
            Interval cur = intervals.get(i);
            if (prev_end < newInterval.start && newInterval.end < cur.start) {  // can insert with no overlap
                intervals.add(i, newInterval);
                inserted = true;
                break;  // finished searching
            }
            if (newInterval.start > cur.end || cur.start > newInterval.end) {  // no overlap with new interval
                prev_end = cur.end;
                i++;
                continue;   
            }
            
            // find an overlap, merge it with the new interval
            newInterval.start = Math.min(cur.start, newInterval.start);
            newInterval.end = Math.max(cur.end, newInterval.end);
            intervals.remove(i);    // remove overlapped interval
        }
        
        if (!inserted) intervals.add(newInterval);  // newInterval is to be added at the end of the list
        return intervals;
    }
}

// Solution 1: recursion solution
// every time we find an interval that is overlapped with the new interval
// we remove the overlapped one from the list, merge it with the new interval,
// and then recursively insert it to the list
// Problem: stack overflow when the number of intervals is huge
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals.size() == 0) {intervals.add(newInterval); return intervals;}
        
        int prev_end = -1;
        for (int i = 0; i < intervals.size(); i++) {
            Interval cur = intervals.get(i);
            if (prev_end < newInterval.start && newInterval.end < cur.start) {  // can insert with no overlap
                intervals.add(i, newInterval);
                break;
            }
            if (newInterval.start > cur.end || cur.start > newInterval.end) {  // no overlap with new interval
                prev_end = cur.end;
                continue;   
            }
            
            // find overlap
            Interval merged = new Interval(Math.min(cur.start, newInterval.start), Math.max(cur.end, newInterval.end));
            intervals.remove(i);    // remove overlapped interval
            return insert(intervals, merged);
        }
        
        // no overlap if reach here
        return intervals;
    }
}

