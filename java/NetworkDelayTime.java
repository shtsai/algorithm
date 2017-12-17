/*
	There are N network nodes, labelled 1 to N.

	Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

	Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

	Note:
	N will be in the range [1, 100].
	K will be in the range [1, N].
	The length of times will be in the range [1, 6000].
	All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 1 <= w <= 100.
 */

// Solution 1: Find the longest single source shortest path
// First convert edges into an adjacency list.
// Then implement Dijkstra's algorithm.
// Time: O(VE)
// Time: O(V + E)
// 12/09/2017
class Solution {
    private class Node {
        int label;
        List<NeighborNode> neighbor;
        int time;
        public Node (int label) {
            this.label = label;
            neighbor = new ArrayList<>();
            time = Integer.MAX_VALUE;
        }
    }
    
    private class NeighborNode {
        int label;
        int cost;
        public NeighborNode (int label, int cost) {
            this.label = label;
            this.cost = cost;
        }
    }
    
    public int networkDelayTime(int[][] times, int N, int K) {
        ArrayList<Node> adj = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            adj.add(new Node(i));
        }
        for (int[] time : times) {
            adj.get(time[0]-1).neighbor.add(new NeighborNode(time[1], time[2]));
        }
        PriorityQueue<Node> q = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node a, Node b) {
                return a.time - b.time;
            }
        });
        adj.get(K-1).time = 0;
        q.offer(adj.get(K-1));
        int res = 0;
        while (!q.isEmpty()) {
            Node node = q.poll();
            int currentTime = node.time;
            for (NeighborNode n : node.neighbor) {
                Node neighbor = adj.get(n.label-1);
                if (neighbor.time > currentTime + n.cost) {
                    neighbor.time = currentTime + n.cost;
                    q.offer(neighbor);
                }
            }
        }
        
        for (int i = 0; i < adj.size(); i++) {
            res = Math.max(res, adj.get(i).time);
        }
        
        return res != Integer.MAX_VALUE ? res : -1;
    }
}