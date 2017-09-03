/*
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
 * 
 * Examples: 
 * "123", 6 -> ["1+2+3", "1*2*3"] 
 * "232", 8 -> ["2*3+2", "2+3*2"]
 * "105", 5 -> ["1*0+5","10-5"]
 * "00", 0 -> ["0+0", "0-0", "0*0"]
 * "3456237490", 9191 -> []
 */

// Solution 1:
// recursive solution, try different cutting points, until reach the end
// time: O(3^n)
// T(n) = 3n * T(n-1); 
// reference: https://discuss.leetcode.com/topic/24523/java-standard-backtrace-ac-solutoin-short-and-clear
class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if (num == null || num.length() == 0) return res;
        helper(num, res, target, 0, 0, 0,"");
        return res;
    }
    
    private void helper(String num, List<String> res, int target, int index, long value, long lastInt, String current) {
        if (index == num.length()) {    // reach the end
            if (target == value) res.add(current);  // and reach our target value
            return;
        }
        
        for (int i = index; i < num.length(); i++) {    // number can have various lengths
            String sub = num.substring(index, i+1);
            if (sub.length() > 1 && sub.charAt(0) == '0') return;   // a number cannot start with '0'
            Long thisNumber = Long.parseLong(sub);
            if (current.length() == 0) {    // first element of the expression, no sign needed
                helper(num, res, target, i+1, thisNumber, thisNumber, thisNumber.toString());
                continue;
            }
            
            // addition
            helper(num, res, target, i+1, value+thisNumber, thisNumber, current+"+"+thisNumber.toString());
            // subtraction    * because we are doing subtraction here, last number needs to * -1
            helper(num, res, target, i+1, value-thisNumber, -thisNumber, current+"-"+thisNumber.toString());
            // multiplication  * we need to remove last value before doing multiplication, otherwise double counting
            helper(num, res, target, i+1, value-lastInt+(lastInt*thisNumber), lastInt*thisNumber, current+"*"+thisNumber.toString());
        }
    }
   
}