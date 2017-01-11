/*
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 *
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 *
 * Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
 *
 */

// mark the position of the appeared numbers
public class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int position = Math.abs(nums[i]) - 1; // need abs here because we might change a positive value to negative
            if (nums[position] > 0) { // if the value is positive, mark it to negative
                nums[position] = -nums[position];
            }
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i+1); // need to add 1 because of offset
            }
        }
        return result;
    }
}

/*
// naive solution
// remove appeared elements from a full set
// then add the remaining elements to a list
public class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= nums.length; i++) {
            set.add(i);
        }
        for (int num : nums) {
            set.remove(num);
        }
        List<Integer> result = new ArrayList<>();
        for (int num : set) {
            result.add(num);
        }
        return result;
    }
}
*/