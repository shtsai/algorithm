/*
	The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the numbers in the set got duplicated to another number in the set, which results in repetition of one number and loss of another number.

	Given an array nums representing the data status of this set after the error. Your task is to firstly find the number occurs twice and then find the number that is missing. Return them in the form of an array.

	Example 1:
	Input: nums = [1,2,2,4]
	Output: [2,3]
	Note:
	The given array size will in the range [2, 10000].
	The given array's numbers won't have any order.
 */

// Solution 4: Assign negative to number we have seen
// When we see the negative number again, we find the duplicate.
// The only positive number in the array represents the spot for the missing number.
// Time: O(n)
// Space: O(1)
// 01/14/2018

class Solution {
    public int[] findErrorNums(int[] nums) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (nums[Math.abs(nums[i]) - 1] < 0) {      // duplicates
                res[0] = Math.abs(nums[i]);
            } else {
                nums[Math.abs(nums[i])-1] *= -1;
            } 
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res[1] = i + 1;
            }
        }
        return res;
    }
}

// Solution 3: HashMap and frequency
// Time: O(n)
// Space: O(n)
// 01/14/2018
class Solution {
    public int[] findErrorNums(int[] nums) {
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            if (!map.containsKey(n)) {
                map.put(n, 1);
            } else {
                map.put(n, map.get(n) + 1);
            }
        }
        for (int i = 1; i <= nums.length; i++) {
            if (!map.containsKey(i)) {
                res[1] = i;
            } else if (map.get(i) == 2) {
                res[0] = i;
            }
        }
        return res;
    }
}

// Solution 2: HashSet
// Add all numbers to HashSet to find the duplicate.
// Then scan from 1 to n to find the missing number.
// Time: O(n) - two-pass
// Space: O(n) - HashSet
// 01/12/2018

class Solution {
    public int[] findErrorNums(int[] nums) {
        int[] res = new int[2];
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.contains(num)) {
                set.add(num);
            } else {
                res[0] = num;
            }
        }       
        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)) {
                res[1] = i;
                break;
            }
        }
        return res;
    }
}

// Solution 1: Sorting
// Sort the numbers and compare adjacent pairs.
// Time: O(nlgn) sorting
// Space: O(1)
// 01/14/2018

class Solution {
    public int[] findErrorNums(int[] nums) {
        int[] res = new int[2];
        int prev = 0;
        Arrays.sort(nums);
        for (int n : nums) {
             if (prev == n) {
                res[0] = n;
            } else if (prev + 1 != n) {
                res[1] = prev + 1;
            }
            prev = n;
        }
        if (res[1] == 0) {
            res[1] = nums.length;
        }
        return res;
    }
}