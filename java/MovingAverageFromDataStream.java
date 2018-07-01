/*
    Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

    For example,
    MovingAverage m = new MovingAverage(3);
    m.next(1) = 1
    m.next(10) = (1 + 10) / 2
    m.next(3) = (1 + 10 + 3) / 3
    m.next(5) = (10 + 3 + 5) / 3
 */

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */

// Solution 1: Queue
// Maintain a queue of given size
// Time: O(1)
// Space: O(n) - n: size of windows
// 07/01/2018

class MovingAverage {
    private Queue<Integer> q;
    private int sum;
    private int size;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.size = size;
        q = new LinkedList<>();
        sum = 0;
    }
    
    public double next(int val) {
        sum += val;
        if (q.size() >= size) {
            sum -= q.poll();
        }
        q.offer(val);
        return (double) sum / q.size();
    }
}

