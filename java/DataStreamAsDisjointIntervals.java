/*
    Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list of disjoint intervals.

    For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:

    [1, 1]
    [1, 1], [3, 3]
    [1, 1], [3, 3], [7, 7]
    [1, 3], [7, 7]
    [1, 3], [6, 7]
    Follow up:
    What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
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


/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * List<Interval> param_2 = obj.getIntervals();
 */

// Solution 1: TreeMap
// Keys are stored in sorted order in a TreeMap
// Use lowerKey() and higherKey() to find keys that are close to the value
// we want to add.
// Time: O(logn) add, O(logn) remove, O(logn) get
// Space: O(n) 
// reference: https://discuss.leetcode.com/topic/46887/java-solution-using-treemap-real-o-logn-per-adding
// 10/16/2017

class SummaryRanges {
    TreeMap<Integer, Interval> map;
    
    /** Initialize your data structure here. */
    public SummaryRanges() {
        map = new TreeMap<>();
    }
    
    public void addNum(int val) {
        if (map.containsKey(val)) return;   // already exist
        Integer lower = map.lowerKey(val);  // greatest key that is lower than val
        Integer higher = map.higherKey(val);// smallest key that is higher than val
        
        if (lower != null && higher != null && map.get(lower).end + 1 == val && val + 1 == higher) {    // between two intervals
            map.get(lower).end = map.get(higher).end;
            map.remove(higher);
        } else if (lower != null && map.get(lower).end + 1 >= val) { // merge with lower
            map.get(lower).end = Math.max(map.get(lower).end, val);
        } else if (higher != null && val + 1 == higher) {
            map.put(val, new Interval(val, map.get(higher).end));
            map.remove(higher);
        } else {
            map.put(val, new Interval(val, val));
        }
    }
    
    public List<Interval> getIntervals() {
        List<Interval> res = new ArrayList<>();
        for (Interval i : map.values()) {
            res.add(i);
        }
        return res;
    }
}
