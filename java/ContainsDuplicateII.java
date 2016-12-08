/*
 * Given an array of integers and an integer k, find out whether there are two distinct 
 * indices i and j in the array such that nums[i] = nums[j] and the difference between i and j is at most k.
 */

public class Solution {
    // use hashMap to keep track of the last occurrence
    public boolean containsNearbyDuplicate(int[] nums, int k) {
       HashMap<Integer, Integer> map = new HashMap<>();
       
       for (int i = 0; i < nums.length; i++) {
           // check if the number already occurred
           // if so, check if the diffrence between the current appearance and
           // the most recent appearance is within k
           if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k) { 
               return true;
           }
           // update the last appearance of this number
           map.put(nums[i], i);
       }
       return false;
    }

    /*
    // use HashSet, maintain at most k elements in the set
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                set.remove(nums[i-k-1]); 
            }
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }
    */
}