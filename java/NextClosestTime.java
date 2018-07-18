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

// Solution 4: Recursive approach
// Similar to solution 3
// 07/17/2018
class Solution {
    int minDiff;
    String res;
    public String nextClosestTime(String time) {
        Set<Integer> digits = new HashSet<>();
        for (char c : time.toCharArray()) {
            if (c >= '0' && c <= '9') {
                digits.add(c - '0');
            }
        }
        int timeInMin = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3, 5));
        minDiff = Integer.MAX_VALUE;
        res = "";       
        helper(digits, 0, "", timeInMin);
        return res;
    }
    
    private void helper(Set<Integer> digits, int index, String current, int start) {
        if (index == 4) {
            int hh = Integer.parseInt(current.substring(0, 2));
            int mm = Integer.parseInt(current.substring(2, 4));
            if (hh >= 24 || mm >= 60) return;
            int diff = hh * 60 + mm - start;
            if (diff <= 0) {
                diff += 1440;
            }
            if (diff < minDiff) {
                minDiff = diff;
                res = String.format("%s:%s", current.substring(0,2), current.substring(2,4));
            }
        } else {
            for (int digit : digits) {
                helper(digits, index + 1, current + digit, start);
            }
        }
    }
}

// Solution 3: Iterative approach
// Try all valid time that can be formed from the digits,
// and return the next closest one.
// Note when diff goes below zero, add 1440 to wrap around.
// 07/17/2018

class Solution {
    public String nextClosestTime(String time) {
        Set<Integer> digits = new HashSet<>();
        for (char c : time.toCharArray()) {
            if (c >= '0' && c <= '9') {
                digits.add(c - '0');
            }
        }
        int timeInMin = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3, 5));
        int minDiff = Integer.MAX_VALUE, res = 0;
        for (int h1 : digits) {
            for (int h2 : digits) {
                if (h1 * 10 + h2 >= 24) {
                    continue;
                }
                for (int m1 : digits) {
                    for (int m2 : digits) {
                        if (m1 * 10 + m2 >= 60) {
                            continue;
                        }
                        int newTimeInMin = (h1 * 10 + h2) * 60 + (m1 * 10) + m2;
                        int newDiff = newTimeInMin - timeInMin;
                        if (newDiff <= 0) {
                            newDiff += 1440;
                        }
                        if (newDiff < minDiff) {
                            minDiff = newDiff;
                            res = newTimeInMin;
                        }
                    }
                }
            }
        }
        return String.format("%02d:%02d", res / 60, res % 60);
    }
}

// Solution 2: Treeset
// Use a tree set to sort all digits in order, log(n) time to find successor.
// 07/17/2018
class Solution {
    public String nextClosestTime(String time) {
        ArrayList<Integer> digits = new ArrayList<>();
        for (char c : time.replace(":", "").toCharArray()) {
            digits.add(c - '0');
        }
        TreeSet<Integer> bst = new TreeSet<Integer>(digits);

        int h1 = digits.get(0);
        int h2 = digits.get(1);
        int m1 = digits.get(2);
        int m2 = digits.get(3);
        
        Integer min = bst.first();
        Integer newM2 = bst.higher(m2);
        if (newM2 != null) {
            return String.format("%d%d:%d%d", h1, h2, m1, newM2);
        }
        Integer newM1 = bst.higher(m1);
        if (newM1 != null && newM1 * 10 + m2 < 60) {
            return String.format("%d%d:%d%d", h1, h2, newM1, min);
        }
        Integer newH2 = bst.higher(h2);
        if (newH2 != null && h1 * 10 + newH2 < 24) {
            return String.format("%d%d:%d%d", h1, newH2, min, min);
        }
        Integer newH1 = bst.higher(h1);
        if (newH1 != null && newH1 * 10 + h2 < 24) {
            return String.format("%d%d:%d%d", newH1, h2, min, min);
        }

        return String.format("%d%d:%d%d", min, min, min, min);
    }
}

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