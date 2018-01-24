/*
	Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

	Example 1:
	Input: [1,2,3,4,5], k=4, x=3
	Output: [1,2,3,4]
	Example 2:
	Input: [1,2,3,4,5], k=4, x=-1
	Output: [1,2,3,4]
	Note:
	The value k is positive and will always be smaller than the length of the sorted array.
	Length of the given array is positive and will not exceed 104
	Absolute value of elements in the array and x will not exceed 104
 */

// Solution 2: Binary Search + Two pointers
// Do binary search to find the first closest position of target.
// Then use two pointers to find k closest number.
// Time: O(lgn + k)
// Space: O(1)
// 01/24/2018

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        int index = binarySearch(arr, x);
        int left = index-1, right = index;
        
        while (k > 0) {
            int leftnum = left >= 0 ? Math.abs(arr[left]-x) : Integer.MAX_VALUE;
            int rightnum = right < arr.length ? Math.abs(arr[right]-x) : Integer.MAX_VALUE;
            if (leftnum <= rightnum) {
                res.add(0, arr[left]);
                left--;
            } else {
                res.add(arr[right]);
                right++;
            }
            k--;
        }
    
        return res;
    }
    
    // find the first occurence of target, or its supposed position.
    public int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length-1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}

// Solution 1:
// Sort the array by distance to target, and extract first k.
// Time: O(nlogn)
// Space: O(n) - create an arraylist
// 01/24/2018

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList();
        for (int a : arr) {
            res.add(a);
        }
        Collections.sort(res, (a, b) -> a == b ? 0 : Math.abs(a-x) - Math.abs(b-x));
        
        res = res.subList(0, k);
        Collections.sort(res);
        
        return res;
    }
}