/*
    Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

    Example 1:
         0          3
         |          |
         1 --- 2    4
    Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

    Example 2:
         0           4
         |           |
         1 --- 2 --- 3
    Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

    Note:
    You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */

// Solution 3: Union find
// Initialize the root of every node to be itself.
// Then scan through edges, union nodes connected by edges.
// Finally, count the number of components.
// Time: O(E) - E is the number of edges
// Space: O(n)
// 01/04/2018
class Solution {
    public int countComponents(int n, int[][] edges) {
        // union find
        int[] root = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }
        for (int[] edge : edges) {
            union(root, edge[0], edge[1]);
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (root[i] == i) {
                count++;
            }
        }
        return count;
    }
    
    public void union(int[] root, int a, int b) {
        int ra = find(root, a);
        int rb = find(root, b);
        if (ra != rb) {
            root[ra] = rb;
        }
    }
    
    public int find(int[] root, int a) {
        if (root[a] != a) {
            root[a] = find(root, root[a]);
        }
        return root[a];
    }
}

// Solution 2: BFS
// Build adjacency list, then do BFS
// Time: O(n^2) = O(n + n^2) - n nodes, n^2 edges
// Space: O(n ^ 2) - adjacency list + visited array + queue
// 01/04/2018

class Solution {
    public int countComponents(int n, int[][] edges) {
        // build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        
        // BFS
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                BFSvisit(i, adj, visited);
                count++;
            }
        }
        return count;
    }
    
    public void BFSvisit(int i, List<List<Integer>> adj, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(i);
        while (!q.isEmpty()) {
            int node = q.poll();
            visited[node] = true;
            for (Integer neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    q.offer(neighbor);
                }
            }
        }
    }
}

// Solution 1: DFS
// Build adjacency list, then do DFS
// Time: O(n^2) = O(n + n^2) - n nodes, n^2 edges
// Space: O(n ^ 2) - adjacency list + visited array
// 01/04/2018

class Solution {
    public int countComponents(int n, int[][] edges) {
        // build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        
        // DFS
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visit(i, adj, visited);
                count++;
            }
        }
        return count;
    }
    
    public void visit(int i, List<List<Integer>> adj, boolean[] visited) {
        visited[i] = true;
        for (Integer neighbor : adj.get(i)) {
            if (!visited[neighbor]) {
                visit(neighbor, adj, visited);
            }
        }
    }
}