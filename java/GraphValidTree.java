/*
    Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

    For example:

    Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

    Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

    Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */

// Solution 1: Union find
// Initialize root of every node to be the node itself.
// When there is an edge connecting two nodes, union them together.
// If the two nodes are in the same tree already, the new edge will create a cycle,
// return false.
// Finally, scan through the root array, make sure there is only one root.
//
// Time: O(elogn + n) - e edges, e calls to union (logn), plus the final scan
// Space: O(n)
// 11/08/2017

class Solution {
    public boolean validTree(int n, int[][] edges) {
        int[] root = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }
        for (int[] edge : edges) {
            if (!union(root, edge[0], edge[1])) {
                return false;
            }
        }
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (root[i] == i) {
                if (found) return false;
                found = true;
            }
        }
        return true;
    }
    
    private boolean union(int[] root, int a, int b) {
        int ra = find(root, a);
        int rb = find(root, b);
        if (ra == rb) return false;
        root[ra] = rb;
        return true;
    }
    
    private int find(int[] root, int a) {
        while (root[a] != a) {
            a = root[a];
        }
        return a;
    }
}