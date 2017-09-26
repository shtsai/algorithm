/*
    Given an array and a window size, first find all minimums in all windows,
    and then return the maximum of these min values.

    For example:
        [2, 5, 4, 6, 7]  3
        Minimum values are 2, 4, 4,
        return 4

    Cloudera
    09/25/2017
 */

class Segment {
    public int segment(int x, int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < x; i++) {   // initialize first segment
            pq.offer(arr[i]);
        }
        int maxMin = pq.peek();  // peek() returns the min in current segment
        
        for (int i = x; i < arr.length; i++) {
            pq.remove(arr[i-x]);    // remove the element that is no longer in the segment
            pq.offer(arr[i]);
            maxMin = Math.max(maxMin, pq.peek());
        }
        return maxMin;
    }
}