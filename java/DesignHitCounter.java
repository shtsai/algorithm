/*
        Design a hit counter which counts the number of hits received in the past 5 minutes.

        Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

        It is possible that several hits arrive roughly at the same time.

        Example:
        HitCounter counter = new HitCounter();

        // hit at timestamp 1.
        counter.hit(1);

        // hit at timestamp 2.
        counter.hit(2);

        // hit at timestamp 3.
        counter.hit(3);

        // get hits at timestamp 4, should return 3.
        counter.getHits(4);

        // hit at timestamp 300.
        counter.hit(300);

        // get hits at timestamp 300, should return 4.
        counter.getHits(300);

        // get hits at timestamp 301, should return 3.
        counter.getHits(301); 
        Follow up:
        What if the number of hits per second could be very large? Does your design scale?
 */


// Solution 2: Circular array
// Use a circular array to keep track of the recent 300s.
// When hit() is called, it will check the time cell corresponding to the timestamp.
// If time matches timestamp, we increment the hit counter. 
// Otherwise, we update time, and reset hit counter to 1.
// When getHits() is called, we scan through the array, summing up hits
// for cells that have a timestamp greater than (timestamp - 300).
// reference: https://discuss.leetcode.com/topic/48758/super-easy-design-o-1-hit-o-s-gethits-no-fancy-data-structure-is-needed
// 
// Time: hit(): O(1)
//       getHits: O(s), where s is the valid time interval, which is 300 in this case
// Space: O(s) 
// 11/02/2017

class HitCounter {
    int[] time;
    int[] hits;
        
    /** Initialize your data structure here. */
    public HitCounter() {
        time = new int[300];
        hits = new int[300];
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        if (time[timestamp%300] != timestamp) {
            time[timestamp%300] = timestamp;
            hits[timestamp%300] = 1;
        } else {
            hits[timestamp%300]++;
        }
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int res = 0;
        for (int i = 0; i < 300; i++) {
            if (time[i] > timestamp-300) {
                res += hits[time[i]%300];
            }
        }
        return res;
    }
}


// Solution 1: ArrayList + TreeMap<Timestamp, Pair>
// Use an arraylist to keep all incoming timestamps in sorted order.
// At the same time, use a treemap to keep track of the start index and
// end index of the corresponding timestamp. This is because there might
// be multiple hits at the same time, which creates multiple entries in
// the arraylist. 
//
// To get the hit count at a particular time, we can use treemap's search
// capability to find the start and end indices. Start index is the smallest
// timestamp that is greater than (timestamp - 300). End index is the largest
// timestamp that is smaller than or equal to input timestamp.
// Once we get start and end time, we can compute the number of timestamps
// between them in the arraylist to get the number of hits.
//
// Time: hit(): O(1) - append to the list, update mapping
//       getHits(): O(logn) - lowerKey() and higherKey()
// Space: O(n) - n is total number of hits
// 11/02/2017

class HitCounter {
    private class Pair {
        int start, end;
        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    ArrayList<Integer> ts;
    TreeMap<Integer, Pair> mapping;
        
    /** Initialize your data structure here. */
    public HitCounter() {
        mapping = new TreeMap<>();
        ts = new ArrayList<>();
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        ts.add(timestamp);
        if (!mapping.containsKey(timestamp)) {
            mapping.put(timestamp, new Pair(ts.size()-1, ts.size()-1));
        } else {
            mapping.get(timestamp).end++;
        }
        
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        Integer end = mapping.floorKey(timestamp);
        Integer start = mapping.higherKey(timestamp - 300);
        if (start == null || end == null) return 0;
        return mapping.get(end).end - mapping.get(start).start + 1;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */