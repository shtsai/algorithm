/*
 * Design a data structure that supports the following two operations:
 *      void addNum(int num) - Add a integer number from the data stream to the data structure.
 *      double findMedian() - Return the median of all elements so far.
 */

public class MedianFinder {
    // use max and min heap to store the top and bottom half of the data
    PriorityQueue<Integer> large = new PriorityQueue<>();  // min heap
    PriorityQueue<Integer> small = new PriorityQueue<>(1000, Collections.reverseOrder());  // max heap
    
    // Adds a number into the data structure.
    public void addNum(int num) {
        large.offer(num);                    // first add to large heap
        small.offer(large.poll());           // then move the smallest element in large to small
        if (large.size() < small.size()) {   // make sure large has more or the same number of elements as small
            large.offer(small.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (large.size() > small.size()) {  // median is in large
            return large.peek();
        } else {      // otherwise median is the average of the two middle value
            return (large.peek() + small.peek()) / 2.0;
        }
    }
};

/*  Solution one, very slow
// maintain a sorted list and the position of the median
public class MedianFinder {
    private ArrayList<Integer> list = new ArrayList<Integer>();
    private int length = 0;
    private int medium = 0;
    
    // Adds a number into the data structure.
    public void addNum(int num) {
        for (int i = 0; i < length; i++) {
            if (list.get(i) >= num) { // find correct insert position
                list.add(i, num);
                length++;
                median = length / 2;
                return;
            }
        }
        // otherwise, insert at the end
        list.add(num);
        length++;
        median = length / 2;
        return;
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (length % 2 == 1) {  // odd length, return the middle number
            return (double) list.get(median);
        } else {  // even length
            return (double) (list.get(median-1) + list.get(median))/2;
        }
    }
};
*/

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();