/*
	Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.

	The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.

	Example:
	Input: [[1,2], [3], [3], []] 
	Output: [[0,1,3],[0,2,3]] 
	Explanation: The graph looks like this:
	0--->1
	|    |
	v    v
	2--->3
	There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
	Note:

	The number of nodes in the graph will be in the range [2, 15].
	You can print different paths in any order, but you should keep the order of nodes inside one path.
 */

// Solution 1: Backtracking
// No need for visited set because the graph is a DAG
// Time: O(n!)
// Space: O(n)
// 07/09/2018

class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        current.add(0);
        helper(graph, 0, current, res);
        return res;
    }
    
    private void helper(int[][] graph, int index, List<Integer> current, List<List<Integer>> res) {
        if (index == graph.length - 1) {
            res.add(new ArrayList<>(current));
            return;
        }
        for (int next : graph[index]) {
            if (next > index) {
                current.add(next);
                helper(graph, next, current, res);
                current.remove(current.size() - 1);               
            }
        }
    }
}