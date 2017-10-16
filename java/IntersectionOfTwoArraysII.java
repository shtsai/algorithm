/*
    Given two arrays, write a function to compute their intersection.

    Example:
    Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

    Note:
    Each element in the result should appear as many times as it shows in both arrays.
    The result can be in any order.
    Follow up:
    What if the given array is already sorted? How would you optimize your algorithm?
    What if nums1's size is small compared to nums2's size? Which algorithm is better?
    What if elements of nums2 are stored on disk, and the memory is limited such that 
    you cannot load all elements into the memory at once?
 */

// Solution 2: HashMap
// Use a HashMap to store each number in nums1 and its occurence.
// Then scan through nums2 to check if number exists in hashMap.
// If found, add it to the result, and decrease the counter for that number.
//
// Time: O(m + n)
// Space: O(m + n)
// 10/16/2017
    
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : nums1) {
            if (!map.containsKey(i)) {
                map.put(i, 1);
            } else {
                map.put(i, map.get(i)+1);
            }
        }
        for (int i : nums2) {
            if (map.containsKey(i) && map.get(i) > 0) {
                list.add(i);
                map.put(i, map.get(i)-1);
            }
        }
        int k = 0;
        int[] res = new int[list.size()];
        for (int i : list) {
            res[k++] = i;
        }
        return res;
    }
}

// Solution 1: Sorting
// Sorting two arrays, and use two pointers to find intersection.
// Time: O(mlogm + nlogn)
// Space: O(min(m, n))
// 10/16/2017

public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        // sort two arrays first
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        // only use one for loop
        // i and j are the respective indice of the two array
        for (int i=0,j=0; i<nums1.length && j<nums2.length;) {
            if (nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        
        // use iterator to convert arrayList to array
        int[] result = new int[list.size()];
        Iterator<Integer> it = list.iterator();
        for (int i = 0; i<list.size(); i++) {
            result[i] = it.next().intValue();
        }
        
        return result;
    }
}