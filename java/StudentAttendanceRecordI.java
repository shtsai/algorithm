/*
 * You are given a string representing an attendance record for a student. The record only contains the following three characters:
 *
 * 'A' : Absent.
 * 'L' : Late.
 * 'P' : Present.
 * A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
 * 
 * You need to return whether the student could be rewarded according to his attendance record.
 * 
 * Example 1:
 * Input: "PPALLP"
 * Output: True
 * Example 2:
 * Input: "PPALLL"
 * Output: False
 */

public class Solution {
    public boolean checkRecord(String s) {
        boolean oneA = false;
        int L = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'A' && oneA) {   // more than one 'A'
                return false;
            } else if (c == 'L' && L == 2) {  // more than two continuous 'L'
                return false;
            }
            
            switch (c) {
                case 'L':
                    L++;
                    break;
                case 'A':
                    oneA = true;
                    L = 0;      // reset L counter
                    break;
                case 'P':
                    L = 0;
                    break;
            }
        }
        return true;
    }
}