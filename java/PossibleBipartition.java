/*
	Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
	Each person may dislike some other people, and they should not go into the same group. 
	Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
	Return true if and only if it is possible to split everyone into two groups in this way.

	Example 1:
	Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
	Output: true
	Explanation: group1 [1,4], group2 [2,3]
	
	Example 2:
	Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
	Output: false
	
	Example 3:
	Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
	Output: false

	Note:
	1 <= N <= 2000
	0 <= dislikes.length <= 10000
	1 <= dislikes[i][j] <= N
	dislikes[i][0] < dislikes[i][1]
	There does not exist i != j for which dislikes[i] == dislikes[j].
 */

// Solution 1: Graph
// Convert dislikes to edges.
// Perform dfs on graph, and check if it is possible to have every connected nodes have different color.
// Time: O(e)
// Space: O(e)
// 08/12/2018

class Solution {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        HashMap<Integer, List<Integer>> edges = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            edges.put(i, new ArrayList<Integer>());
        }
        for (int[] d : dislikes) {
            edges.get(d[0]).add(d[1]);
            edges.get(d[1]).add(d[0]);
        }
        HashMap<Integer, Boolean> colors = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            if (!colors.containsKey(i) && !dfs(edges, colors, 1, true)) {
                return false;
            }
        } 
        return true;
    }
    
    private boolean dfs(HashMap<Integer, List<Integer>> edges, HashMap<Integer, Boolean> colors, int n, boolean group) {
        if (colors.containsKey(n)) {
            return colors.get(n) == group;
        }
        colors.put(n, group);
        for (Integer e : edges.get(n)) {
            if (!dfs(edges, colors, e, !group)) {
                return false;
            }
        }
        return true;
    }
}
