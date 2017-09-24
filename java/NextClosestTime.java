/*
 * Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no limit on how many times a digit can be reused.
 * 
 * You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.
 * 
 * Example 1:
 * 
 * Input: "19:34"
 * Output: "19:39"
 * Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours and 59 minutes later.
 * Example 2:
 * 
 * Input: "23:59"
 * Output: "22:22"
 * Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.
 */

// Solution 1: naive solution
// Handle all 4 digits manually.
// First store all available digits in sorted array.
// Then check if each digit from right to left.
// If a digit can be replaced by a larger one, return the result.
// If not, set the digit to the min value, and move on to the digit on the left.
// 09/23/2017
class Solution {
    public String nextClosestTime(String time) {
        char[] numbers = new char[4];
        char[] chars = time.toCharArray();
        numbers[0] = chars[0];
        numbers[1] = chars[1];
        numbers[2] = chars[3];
        numbers[3] = chars[4];
        Arrays.sort(numbers);
        HashMap<Character, Integer> map = new HashMap<>();
        map.put(numbers[0], 0);
        map.put(numbers[1], 1);
        map.put(numbers[2], 2);
        map.put(numbers[3], 3);
        
        int index = map.get(chars[4]);
        if (index < 3) {    // can change with a large number
            chars[4] = numbers[index+1] ;
            return new String(chars);
        } else {
            chars[4] = numbers[0];  // cannot increase, change to smallest
        }
        index = map.get(chars[3]);
        if (index < 3 && numbers[index+1]-'0' < 6) {  // will exceed 60
                chars[3] = numbers[index+1];
                return new String(chars);
        } else {
            chars[3] = numbers[0];
        }
        
        index = map.get(chars[1]);
        if (index < 3 && ((chars[0]-'0')*10 + (numbers[index+1]-'0')) < 24) {
                chars[1] = numbers[index+1];
                return new String(chars);
        } else {
            chars[1] = numbers[0];
        }
        
        index = map.get(chars[0]);
        if (index < 3 && (numbers[index+1]-'0')*10 + (chars[1]-'0') < 24) {
                chars[0] = numbers[index+1];
                return new String(chars);
        } else {
            chars[0] = numbers[0];
        }
        
        return new String(chars);
    }
}