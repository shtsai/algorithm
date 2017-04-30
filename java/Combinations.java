/*
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 *
 * For example,
 * If n = 4 and k = 2, a solution is:
 *
 * [
 *  [2,4],
 *  [3,4],
 *  [2,3],
 *  [1,2],
 *  [1,3],
 *  [1,4],
 * ]
 */
// backtracking
public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> list = new ArrayList<>();        
        for (int i = 1; i <= n; i++) {
            List<Integer> a = new ArrayList<>();
            a.add(i);
            helper(a, list, i+1, n, k-1);
        }
        return list;
    }
    
    public void helper(List<Integer> a, List<List<Integer>> list, int i, int n, int k) {
        // base case, have k nums in the list
        if (k == 0) {
            // create a deep copy of the list
            list.add(new ArrayList<Integer>(a));
            
            /* alternative way to create a deep copy
            List<Integer> clone = new ArrayList<>();
            clone.addAll(a);
            list.add(clone);
            */

            return;
        }
        for (int j = i; j <= n; j++) {
            // add the next num, and remove it later
            a.add(j);
            helper(a, list, j+1, n, k-1);
            a.remove(a.size()-1);
        }
        return;
    }
}