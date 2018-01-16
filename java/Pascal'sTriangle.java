/*
 * Given numRows, generate the first numRows of Pascal's triangle.
 */

// Solution 2:
// A more concise solution.
// Build up results level by level. Each level depends on previous level.
// Time: O(n^2)
// Space: O(1)
// version 2:
// 01/15/2018
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            if (i == 0) {
                list.add(1);
            } else {
                for (int j = 0; j <= i; j++) {
                    int first = j - 1 >= 0 ? res.get(i-1).get(j-1) : 0;
                    int second = j < i ? res.get(i-1).get(j) : 0;
                    list.add(first + second);
                }
            }
            res.add(list);
        }
        return res;
    }
}

// version 1:
public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
    
        for (int i = 0; i < numRows; i++) {     // i stands for row number
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < i+1; j++) {     // j stands for col number
                if (j==0 || i==j) {     // add 1 if it is the first element or the last element
                    list.add(1);
                } else {   // otherwise, go to previous row, and get the sum of two numbers above
                    list.add(result.get(i-1).get(j-1) + result.get(i-1).get(j));
                }
            }
            result.add(list);
        }
        
        return result;
    }
}

// Solution 1:
// Handle special cases manually.
// Time: O(n^2)
// Space: O(1)
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows == 0) {     // special case 0
            return result;
        }
    
        List<Integer> list = new ArrayList<>();
        list.add(1);
        result.add(list);
        if (numRows == 1) {     // special case 1
            return result;
        } 
        
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(1);
        result.add(list2);
        if (numRows == 2) {     // special case 2
            return result;
        }
        
        for (int i = 2; i < numRows; i++) {
            List<Integer> newlist = new ArrayList<>();
            newlist.add(1);                         // add the first 1
            List<Integer> prev = result.get(i-1);   // get previous row
            for (int j = 1; j < i; j++) {
                newlist.add(prev.get(j-1) + prev.get(j));   // the elements are based on previous row
            }
            newlist.add(1);                         // append 1 at the end
            result.add(newlist);
        }
        
        return result;
    }
}
