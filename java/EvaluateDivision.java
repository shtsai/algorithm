/*
	Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

	Example:
	Given a / b = 2.0, b / c = 3.0. 
	queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
	return [6.0, 0.5, -1.0, 1.0, -1.0 ].

	The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

	According to the example above:

	equations = [ ["a", "b"], ["b", "c"] ],
	values = [2.0, 3.0],
	queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
	The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 */

// Solution 1: Graph
// Convert the problem into a graph, where the edge weight represents ratio.
// Then we can do dfs to find relationship between variables.
//
// Time: buildGraph - O(n)
//       search - O(n)
// Space: O(n)
// 08/27/2018

class Solution {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        HashMap<String, HashMap<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
            String[] equation = equations[i];
            double value = values[i];
            graph.computeIfAbsent(equation[0], x -> new HashMap<>()).put(equation[1], value);
            graph.computeIfAbsent(equation[1], x -> new HashMap<>()).put(equation[0], 1.0 / value);
        }
        
        double[] res = new double[queries.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = solve(graph, queries[i]);
        }
        return res;
    }
    
    private double solve(HashMap<String, HashMap<String, Double>> graph, String[] query) {
        if (!graph.containsKey(query[0]) || !graph.containsKey(query[1])) {
            return -1.0;
        } else if (graph.get(query[0]).containsKey(query[1])) {
            return graph.get(query[0]).get(query[1]);
        } else {
            HashSet<String> visited = new HashSet<>();
            double res = search(graph, visited, query[0], query[1], 1.0);
            if (res != -1.0) {
                graph.get(query[0]).put(query[1], res);
            }
            return res;
        }
    }
    
    private double search(HashMap<String, HashMap<String, Double>> graph, HashSet<String> visited, String cur, String target, double value) {
        if (cur.equals(target)) {
            return value;
        } else if (visited.contains(cur)) {
            return -1.0;
        } else {
            visited.add(cur);
            for (String s : graph.get(cur).keySet()) {
                double res = search(graph, visited, s, target, value * graph.get(cur).get(s));
                if (res != -1.0) {
                    return res;
                }
            }
            return -1.0;
        }
    }
}