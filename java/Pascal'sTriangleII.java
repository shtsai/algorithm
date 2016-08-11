/**
 * Given an index k, return the kth row of the Pascal's triangle.
 * For example, given k = 3,
 * Return [1,3,3,1].
 */


// use recursion to break down the problem
public class Solution {
    public List<Integer> getRow(int rowIndex) {
        
        List<Integer> result = new ArrayList<Integer>();
        
        // base case
        if (rowIndex == 0) {
            List<Integer> zero = new ArrayList<Integer>();
            zero.add(1);
            return zero;
        }
        
        // get previous level
        // use previous level to obtain the current level
        List<Integer> previous = getRow(rowIndex - 1);
        int prev = 0;
        int size = previous.size();
        for (int i = 0; i < size; i++) {
            int current = previous.get(i);
            result.add(current + prev);
            prev = current;
        }
        // add the last element to the result
        result.add(previous.get(size-1));

        return result;
    }
}