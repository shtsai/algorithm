/*
	Given a sorted array of integers nums and integer values a, b and c. Apply a quadratic function of the form f(x) = ax2 + bx + c to each element x in the array.

	The returned array must be in sorted order.

	Expected time complexity: O(n)

	Example 1:
	Input: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
	Output: [3,9,15,33]
	
	Example 2:
	Input: nums = [-4,-2,2,4], a = -1, b = 3, c = 5
	Output: [-23,-5,1,7]
 */

// Solution 2: Property of quadratic function
// y = a * x ^ 2 + b * x + c
// If a > 0, then the function is concave up. Larger values on both sides.
// If a < 0, then the function is concave down. Larger values in the middle.
// We can use this property, and do a merge sort like process.
// 
// Time: O(n)
// Space: O(1)
// 09/11/2018
class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int[] res = new int[nums.length];
        int left = 0, right = nums.length - 1;
        int index = a >= 0 ? nums.length - 1 : 0;
        while (0 <= index && index < nums.length) {
            int leftV = a * nums[left] * nums[left] + b * nums[left] + c;
            int rightV = a * nums[right] * nums[right] + b * nums[right] + c;
            if (a >= 0) {    
                if (leftV < rightV) {
                    res[index] = rightV;
                    right--;
                } else {
                    res[index] = leftV;
                    left++;
                }
                index--;
            } else {
                if (leftV < rightV) {
                    res[index] = leftV;
                    left++;
                } else {
                    res[index] = rightV;
                    right--;
                }
                index++;
            }
        }
        return res;
    }
}

// Solution 1: Naive + PriorityQueue
// Time: O(nlogn)
// Space: O(n)
// 09/10/2018

class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int n : nums) {
            pq.offer(a * n * n + b * n + c);
        }
        int[] res = new int[nums.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = pq.poll();
        }
        return res;
    }
}