/*
 * Given a non-empty array of integers, 
 * return the third maximum number in this array. 
 * If it does not exist, return the maximum number. 
 * The time complexity must be in O(n).
 */

// Solution 1: priority queue
public class Solution {
    public int thirdMax(int[] nums) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        
        for (int i = 0; i < nums.length; i++) {
            if (q.contains(nums[i])) {continue;}
            else if (q.size() < 3) {
                q.add(nums[i]);
            } else {
                if (q.peek() < nums[i]) {
                    q.poll();
                    q.add(nums[i]);
                }
            }
        }
        
        if (q.size() == 3) return q.poll();
        else {
            int result = 0;
            while (q.size() > 0) {
                result = q.poll();
            }
            return result;
        }
    }
}

// Solution 2: three variables
public class Solution {
    public int thirdMax(int[] nums) {
        Integer max, mid, small;
        max = mid = small = null;

        for(Integer x: nums) {
            // Skip loop if it is a duplicate
            if(x.equals(max) || x.equals(mid) || x.equals(small)) {
                continue;
            }

            if (max == null || x > max) {
                //right shift
                small = mid;
                mid = max;
                max = x;
            } else if(mid == null || x > mid) {
                //right shift
                small = mid;
                mid = x;
            } else if (small == null || x > small) {
                small = x;
            }
        }

        if (small == null || mid == null) return max; 
        else return small;
    }
}