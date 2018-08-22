/*
	Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.

	Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.

	A double booking happens when two events have some non-empty intersection (ie., there is some time that is common to both events.)

	For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.

	Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
	Example 1:
	MyCalendar();
	MyCalendar.book(10, 20); // returns true
	MyCalendar.book(15, 25); // returns false
	MyCalendar.book(20, 30); // returns true
	Explanation: 
	The first event can be booked.  The second can't because time 15 is already booked by another event.
	The third event can be booked, as the first event takes every time less than 20, but not including 20.
	Note:

	The number of calls to MyCalendar.book per test case will be at most 1000.
	In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].
 */

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */

// Solution 2: TreeSet
// Store Intervals in a TreeSet, sorted by start time.
// Time: initialize - O(1)
//       book - O(logn)
// Space: O(n)
// 08/22/2018

class MyCalendar {
    class Interval implements Comparable<Interval> {
        int start;
        int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
        public int compareTo(Interval other) {
            return this.start - other.start;
        }
    }
    TreeSet<Interval> intervals;
    public MyCalendar() {
        intervals = new TreeSet<>();
    }
    
    public boolean book(int start, int end) {
        Interval current = new Interval(start, end);
        Interval prev = intervals.floor(current);
        if (prev != null && prev.end > start) {
            return false;
        }
        Interval next = intervals.ceiling(current);
        if (next != null && end > next.start) {
            return false;
        }
        intervals.add(current);
        return true;        
    }
}


// Solution 1:
// Maintain a (unsorted) list of intervals.
// Time: initialize - O(1)
//       book - O(n)
// Space: O(n)
// 08/22/2018

class MyCalendar {
    class Interval {
        int start;
        int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    List<Interval> intervals;
    public MyCalendar() {
        intervals = new ArrayList<>();
    }
    
    // check with all current intervals
    public boolean book(int start, int end) {
        for (Interval interval : intervals) {
            if (!(interval.start >= end || start >= interval.end)) {
                return false;
            }
        }
        intervals.add(new Interval(start, end));
        return true;
    }
}

