/*
     A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

    Write a function to determine if a number is strobogrammatic. The number is represented as a string.

    For example, the numbers "69", "88", and "818" are all strobogrammatic.
 */

// Solution 2:
// Store all possible pattern in a string,
// Use two pointers to scan from left and right,
// see if left and right element corresponds to a pattern in the string.
// Time: O(n) - better performance compared to solution 1
// Space: O(1) 
// reference: https://discuss.leetcode.com/topic/20837/4-lines-in-java
    
public boolean isStrobogrammatic(String num) {
    for (int i=0, j=num.length()-1; i <= j; i++, j--)
        if (!"00 11 88 696".contains(num.charAt(i) + "" + num.charAt(j)))
            return false;
    return true;
}

// Solution 1:
// Store all valid mappings in a hashmap.
// Scan through the array using two pointers from left and right,
// check if each corresponding position matches.
// If the length is odd, need to check the middle character.
// Time: O(n) - one pass
// Space: O(1) - constant space
// 10/07/2017

class Solution {
    public boolean isStrobogrammatic(String num) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');
        map.put('6', '9');
        map.put('9', '6');
        
        int left = 0, right = num.length()-1;
        while (left < right) {
            if (!map.containsKey(num.charAt(left))) return false;
            if (map.get(num.charAt(left))!=num.charAt(right)) return false;
            left++;
            right--;
        }
        if (left == right) {
            char c = num.charAt(left);
            return c == '0' || c == '1' || c == '8';
        }
        return true;
    }
}