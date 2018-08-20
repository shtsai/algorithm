/*
	We are given a list schedule of employees, which represents the working time for each employee.

	Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

	Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

	Example 1:
	Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
	Output: [[3,4]]
	Explanation:
	There are a total of three employees, and all common
	free time intervals would be [-inf, 1], [3, 4], [10, inf].
	We discard any intervals that contain inf as they aren't finite.

	Example 2:
	Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
	Output: [[5,6],[7,9]]
	(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)

	Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

	Note:
	schedule and schedule[i] are lists with lengths in range [1, 50].
	0 <= schedule[i].start < schedule[i].end <= 10^8.
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

// Solution 2: Maintain heads of the lists
// Similar to merge K sorted lists
// Maintain a min heap of list heads, sort by start time
//
// Time: O(nlogm) - n:len(a list), m:# of lists
// Space: O(m) - priority queue, m list heads
// 08/20/2018

class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        PriorityQueue<Integer[]> pq = new PriorityQueue<>((a, b) -> {
            int aStart = schedule.get(a[0]).get(a[1]).start;
            int bStart = schedule.get(b[0]).get(b[1]).start;
            return aStart - bStart;
        });
        int end = Integer.MAX_VALUE;
        for (int i = 0; i < schedule.size(); i++) {
            end = Math.min(end, schedule.get(i).get(0).start);
            pq.offer(new Integer[] {i, 0});
        }
        
        List<Interval> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            Integer[] p = pq.poll();
            int pIndex = p[0];
            int jIndex = p[1];
            Interval newjob = schedule.get(pIndex).get(jIndex);
            if (newjob.start > end) {   // capture free time
                res.add(new Interval(end, newjob.start));
            }
            end = Math.max(end, newjob.end);
            if (jIndex < schedule.get(pIndex).size() - 1) {
                p[1]++;
                pq.offer(p);
            }
        }
        return res;
    }
}

// Solution 1: Line Sweep
// For each interval, create two timestamp: -start, end
// Sort all timestamp by increasing absolute values
// Scan through each timestamp, find intervals where current = 0.
//
// Time: O(nlogn) - sort
// Space: O(n)
// 08/20/2018

class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        // Flatten list
        List<Interval> xs = schedule.stream().flatMap(List::stream).collect(Collectors.toList());
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return Math.abs(a) - Math.abs(b);
        });
        for (Interval x : xs) {
            pq.offer(x.start * -1);
            pq.offer(x.end);
        }
        
        List<Interval> res = new ArrayList<>();
        int current = 0;
        int start = -1;
        while (!pq.isEmpty()) {
            int p = pq.poll();
            if (p > 0) {
                current--;
                if (current == 0) {
                    start = p;
                }
            } else {
                // make sure new interval is valid
                if (current == 0 && start != -1 && start != (p * -1)) {
                    res.add(new Interval(start, p * -1));
                }
                current++;
            }
        }
        return res;
    }
}