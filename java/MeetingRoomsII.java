/*
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 * 
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return 2. 
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

// Solution 3: Sweeping line
// For each interval, create two events: start (1) and end (-1).
// Sort the events by time stamps, and end comes before start.
// Then use a counter to accumulate the values.
// The max counter is the result.
//
// Time: O(nlogn)
// Space: O(n)
// 09/07/2018

class Solution {
    class Event implements Comparable<Event> {
        int time;
        int type;
        public Event(int time, int type) {
            this.time = time;
            this.type = type;
        }
        public int compareTo(Event other) {
            if (this.time != other.time) {
                return this.time - other.time;
            } else {
                return this.type - other.type;
            }
        }
    }
    public int minMeetingRooms(Interval[] intervals) {
        PriorityQueue<Event> pq = new PriorityQueue<>();
        for (Interval i : intervals) {
            pq.offer(new Event(i.start, 1));
            pq.offer(new Event(i.end, -1));
        }
        int count = 0;
        int max = 0;
        while (!pq.isEmpty()) {
            Event e = pq.poll();
            count += e.type;
            max = Math.max(max, count);
        }
        return max;
    }
}

// Solution 2: Greedy
// First sort the intervals by start time.
// Then use a priorityqueue to maintain the first finish time.
// When examine a new interval, there are two possible cases:
//      1. the new interval starts after the first finish time,
//         we can merge two intervals and put it back to the queue
//      2. the new interval starts earlier than the first finish time,
//         thus there is no existing room suitable for it. Need to 
//         open a new room
// Note that since intervals are sorted by start time,
// this guarantees the greedy property.
// Time: O(nlogn) - sorting
// Space: O(n) - worst case need to have a room for every interval
// 09/23/2017

class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        List<List<Interval>> rooms = new ArrayList<>();
        Arrays.sort(intervals, new Comparator<Interval>() { // sort by start time
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        PriorityQueue<Interval> q = new PriorityQueue<>(intervals.length, new Comparator<Interval>() {
            @Override       // sort by acsending end time
            public int compare(Interval a, Interval b) {
                return a.end - b.end;
            }
        });
        for (Interval interval : intervals) {
            if (q.size() == 0) {
                q.offer(interval);
                continue;
            }
            Interval firstFin = q.poll();
            if (firstFin.end <= interval.start) {  // finish between next start
                firstFin.end = interval.end;
            } else {    // even the first finished is later than next start
                q.offer(interval);
            }
            q.offer(firstFin);
        }
        return q.size();
    }
}

// Solution 1: Greedy
// First sort the array by starting time.
// Then greedily add the intervals to the first available room.
// When no room is suitable for the current interval, put it in a 
// new room.
// Time: O(nlogn) - sorting
// Space: O(n) - store all intervals in list
// 09/23/2017

class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        List<List<Interval>> rooms = new ArrayList<>();
        Arrays.sort(intervals, new Comparator<Interval>() { // sort by start time
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        for (Interval interval : intervals) {
            boolean added = false;
            for (List<Interval> room : rooms) {
                if (addMeeting(interval, room)) { // successfully add
                    added = true;
                    break;
                }
            }
            if (!added) {   // no suitable room
                List<Interval> newroom = new ArrayList<Interval>();
                newroom.add(interval);
                rooms.add(newroom);
            }
        }
        return rooms.size();
    }
    
    public boolean addMeeting(Interval interval, List<Interval> room) {
        if (room.size() == 0) {     // this room is empty
            room.add(interval);
            return true;
        }
        if (interval.end <= room.get(0).start) {  // ends before first one starts
            room.add(0, interval);
            return true;
        }
        if (interval.start >= room.get(room.size()-1).end) { // starts after last one ends
            room.add(interval);
            return true;
        }
        for (int i = 1; i < room.size(); i++) {
            if (interval.start >= room.get(i-1).end && interval.end <= room.get(i).start) {
                room.add(i, interval);
                return true;
            } 
        }
        return false;
    }
}