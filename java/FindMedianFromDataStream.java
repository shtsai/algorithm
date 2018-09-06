/*
    Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

    Examples: 
    [2,3,4] , the median is 3

    [2,3], the median is (2 + 3) / 2 = 2.5

    Design a data structure that supports the following two operations:

    void addNum(int num) - Add a integer number from the data stream to the data structure.
    double findMedian() - Return the median of all elements so far.
    For example:

    addNum(1)
    addNum(2)
    findMedian() -> 1.5
    addNum(3) 
    findMedian() -> 2
 */

// Solution 3: Two Heaps (priority queue)
// One priority stores number greater than the median.
// The other stores number less than median.
// To get median, if the size of the two heaps are the same,
// return the average of two peek().
// If one heap is larger than the other, then return the peek() of the
// heap with larger size.
//
// Time: O(logn) add, O(1) get
// Space: O(n)
// version 2:
// 10/26/2017

class MedianFinder {
    PriorityQueue<Integer> smaller;
    PriorityQueue<Integer> larger;
    
    /** initialize your data structure here. */
    public MedianFinder() {
        smaller = new PriorityQueue<Integer>(Collections.reverseOrder());
        larger = new PriorityQueue<Integer>();
    }
    
    public void addNum(int num) {
        if (smaller.isEmpty() || num <= smaller.peek()) {
            smaller.offer(num);
        } else {
            larger.offer(num);
        }
        // make sure smaller is equal to larger or has one more element
        while (smaller.size() > larger.size() + 1) {
            larger.offer(smaller.poll());
        }
        while (smaller.size() < larger.size()) {
            smaller.offer(larger.poll());
        }
    }
    
    public double findMedian() {
        if ((smaller.size() + larger.size()) % 2 == 0) {
            return (smaller.peek() + larger.peek()) / 2.0;
        } else {
            return smaller.peek();
        }
    }
}

// Solution 2: LinkedList
// maintain a sorted linkedlist, and a counter for the length
// No need to shift over when insert, but do not have random access.
// Time: O(n) insert, O(n) get  (Time Limit Exceeded)
// Space: O(n)
// 10/26/2017
class MedianFinder {
    /** initialize your data structure here. */
    LinkedList<Integer> nums;
    int length;
    
    public MedianFinder() {
        nums = new LinkedList<>();
        length = 0;
    }
    
    public void addNum(int num) {
        boolean added = false;
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) > num) { // insert at the sorted position
                nums.add(i, num);
                added = true;
                break;
            }
        }
        if (!added) nums.add(num);
        length++;

    }
    
    public double findMedian() {
        
        System.out.println(length);
        if (length == 0) return 0;
        if (length == 1) return nums.get(0);
        if (length % 2 == 1) {
            return nums.get(length/2);
        } else {
            return (nums.get((length/2)-1) + nums.get(length/2)) / 2.0;
        }
    }
}

// Solution 1: ArrayList
// maintain a sorted list and the position of the median
// Time: O(n) insert (need shift), O(1) get
// Space: O(n)

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