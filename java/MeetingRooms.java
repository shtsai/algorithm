/* 
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
 * 
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return false. 
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

// Solution 1: Sort and check
// Easy solution, first sort the meetings by start time,
// then iteratively check if a meeting starts before another
// ends.
// Time: O(nlogn) - due to sorting
// Space: O(1)
// 09/22/2017

class Solution {
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return true;
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                if (i1.start == i2.start) {
                    return i1.end - i2.end;
                } else {
                    return i1.start - i2.start;
                }
            }
        });
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < intervals[i-1].end) { // a meeting starts before another ends
                return false;
            }
        }
        return true;
    }
}