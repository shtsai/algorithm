/*
 * For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.
 *
 * Format
 * The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).
 * 
 * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */

// repeated remove the leaves until there are less than 2 nodes left,
// the remaining nodes are the center of the trees, the roots of the min height trees
public class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) return Collections.singletonList(0);
        
        List<Integer>[] list = new List[n];        // adjacency list
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        int[] inDegree = new int[n];
        
        for (int i = 0; i < edges.length; i++) {
            list[edges[i][0]].add(edges[i][1]);    // edges are undirected
            list[edges[i][1]].add(edges[i][0]);
            inDegree[edges[i][0]]++;
            inDegree[edges[i][1]]++;
        }
        
        List<Integer> leaves = new ArrayList<>();    // store leaves, which are nodes whose in degree is 1
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 1) leaves.add(i);
        }

        while (n > 2) {
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();   // store new leaves     
            for (int leave : leaves) {
                for (int neighbor : list[leave]) {
                    list[neighbor].remove((Integer) leave);    // remove the edge
                    inDegree[neighbor]--;
                    if (inDegree[neighbor] == 1) newLeaves.add(neighbor);
                }
            }
            leaves = newLeaves;
        }
        
        return leaves;
        
    }
}