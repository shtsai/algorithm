/*
 * Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.
 */

public class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> result = new ArrayList<>();
        
        // try to divide the problem at different spots
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                String left = input.substring(0,i);
                String right = input.substring(i+1, input.length());
                
                // recursively solve left and right halves
                List<Integer> leftList = diffWaysToCompute(left);
                List<Integer> rightList = diffWaysToCompute(right);
                
                // compute the results for different grouping
                for (Integer x : leftList) {
                    for (Integer y : rightList) {
                        if (c == '+') {
                            result.add(x + y);
                        } else if (c == '-') {
                            result.add(x - y);
                        } else {
                            result.add(x * y);
                        }
                    }
                }
            }
        }
        
        // base case
        // if result list is empty, this means that there is no operator in the input
        // we just need to parse the integer value
        if (result.size() == 0) {
            result.add(Integer.valueOf(input));
        }
        
        return result;
    }
}