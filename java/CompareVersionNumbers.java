/*
 * Compare two version numbers version1 and version2.
 * If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
 * 
 * You may assume that the version strings are non-empty and contain only digits and the . character.
 * The . character does not represent a decimal point and is used to separate number sequences.
 * For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.
 * 
 * Here is an example of version numbers ordering:
 * 
 * 0.1 < 1.1 < 1.2 < 13.37
 */

// compare version number part by part, be carefully when the lengths are different
// reference: https://discuss.leetcode.com/topic/6238/accepted-small-java-solution
class Solution {
    public int compareVersion(String version1, String version2) {
        if (version1.length() == 0 || version2.length() == 0) return 0;
        String[] array1 = version1.split("\\.");
        String[] array2 = version2.split("\\.");
        int len = Math.max(array1.length, array2.length);
        
        for (int i = 0; i < len; i++) {
            Integer one = i < array1.length ? Integer.parseInt(array1[i]) : 0;
            Integer two = i < array2.length ? Integer.parseInt(array2[i]) : 0;
            int compare = one.compareTo(two);
            if (compare != 0) return compare;
        }
        return 0;
    }
}