/*
    Given two arrays, write a function to compute their intersection.

    Example:
    Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

    Note:
    Each element in the result must be unique.
    The result can be in any order.
 */

// Solution 4: Sorting + Binary Search
// Sort one array, then scan through the other array
// and do binary search on the sorted array.
// 
// Time: O(mlogm + nlogm) - mlogm for sorting, nlogm for n binary searches
// Space: O(min(m, n))
// 10/16/2017
    
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Set<Integer> set = new HashSet<>();
        for (int i : nums2) {
            if (binarySearch(nums1, i)) {
                set.add(i);
            }
        }
        int[] res = new int[set.size()];
        int k = 0;
        for (int i : set) {
            res[k++] = i;
        }
        return res;
    }
    
    private boolean binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}

// Solution 3: Two HashSets
// Put all numbers in nums1 into a set1 to remove duplicates and enable quick lookup.
// Scan through nums2, add all numbers that are also in set1 to set2.
// The result is the numbers in set2.
// Time: O(m + n) - two passes
// Space: O(m + n) - two hashSet
// 10/16/2017

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        
        for (int i : nums1) {
            set1.add(i);
        }
        for (int i : nums2) {
            if (set1.contains(i)) {
                set2.add(i);
            }
        }
        int[] res = new int[set2.size()];
        Iterator it = set2.iterator();
        for (int i = 0; i < set2.size(); i++) {
            res[i] = (int) it.next();
        }
        return res;
    }
}

// Solution 2: sorting
// Sorting the two arrays to ascending order.
// Use two pointers to scan through two arrays, and use another
// pointer to write to the result array.
// Searching by repeatedly incrementing the pointer with smaller value
// until the value of two pointers are the same, which is an intersection.
// Need to check for duplicates.
// 
// Time: O(nlogn + mlogm) - sorting
// Space: O(min(m, n))
// 10/16/2017

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] res = new int[Math.min(nums1.length, nums2.length)];
        int i = 0, j = 0, k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                if (k == 0 || (k > 0 && res[k-1] != nums1[i])) {
                    res[k++] = nums1[i];
                }
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                i++;
            }
        }
        return Arrays.copyOfRange(res, 0, k);
    }
}

// Solution 1: Brute force
// For every number in nums1, check if it is in nums2.
// If so, add it to result. Otherwise, discard it.
// Time: O(mn)
// Space: O(min(m, n))
