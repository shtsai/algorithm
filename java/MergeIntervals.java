/*
 * Given a collection of intervals, merge all overlapping intervals.
 * 
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
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

// sort by start time, then merge
public class Solution {
    private class Cmp implements Comparator<Interval> {
        public int compare(Interval x, Interval y) {
            return x.start - y.start;
        }
    }
    
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) return res;
        
        Collections.sort(intervals, new Cmp());     // sort by accending start time
        
        int start = -1, end = -1;
        for (Interval i : intervals) {
            if (start == -1 && end == -1) {     // new interval
                start = i.start;
                end = i.end;
                continue;
            }
            
            if (i.start > end) {    // no overlap
                res.add(new Interval(start, end));
                start = i.start;
                end = i.end;
            } else {                // merge two intervals
                end = Math.max(end, i.end);
            }
        }
        res.add(new Interval(start, end));      // add last interval
        
        return res;
    }
}