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

// Solution 1: sort by start time, then merge
// Time: O(nlogn) - sorting + one passes
// Space: O(1)

// Version 3: lambda function
// 08/14/2018
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, (Interval a, Interval b) -> {
            if (a.start == b.start) {
                return a.end - b.end;
            } else {
                return a.start - b.start;
            }
        });

        List<Interval> res = new ArrayList<>();
        Interval prev = null;
        for (Interval i : intervals) {
            if (prev == null) {
                prev = i;
            } else if (prev.end >= i.start) {  // overlap
                prev.end = Math.max(prev.end, i.end);
            } else {
                res.add(prev);
                prev = i;
            }
        }
        if (prev != null) {
            res.add(prev);
        }
        return res;
    }
}

// Version 2: anomynous comparator
// 01/05/2018
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare (Interval a, Interval b) {
                if (a.start == b.start) {
                    return a.end - b.end;
                } else {
                    return a.start - b.start;
                }
            }
        });
        List<Interval> res = new ArrayList<>();
        Interval cur = null;
        for (Interval i : intervals) {
            if (cur == null) {
                cur = i;
                continue;
            } else if (i.start <= cur.end) {
                cur.end = Math.max(cur.end, i.end);
            } else {
                res.add(cur);
                cur = i;
            }
        }
        if (cur != null) res.add(cur);
        return res;
    }
}

// version 1: write comparator class
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
