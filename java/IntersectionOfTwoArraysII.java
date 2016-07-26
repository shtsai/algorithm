/**
 * Given two arrays, write a function to compute their intersection.
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
 */

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