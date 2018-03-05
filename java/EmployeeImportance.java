/*
    You are given a data structure of employee information, which includes the employee's unique id, his importance value and his direct subordinates' id.

    For example, employee 1 is the leader of employee 2, and employee 2 is the leader of employee 3. They have importance value 15, 10 and 5, respectively. Then employee 1 has a data structure like [1, 15, [2]], and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []]. Note that although employee 3 is also a subordinate of employee 1, the relationship is not direct.

    Now given the employee information of a company, and an employee id, you need to return the total importance value of this employee and all his subordinates.

    Example 1:
    Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
    Output: 11
    Explanation:
    Employee 1 has importance value 5, and he has two direct subordinates: employee 2 and employee 3. They both have importance value 3. So the total importance value of employee 1 is 5 + 3 + 3 = 11.
    Note:
    One employee has at most one direct leader and may have several subordinates.
    The maximum number of employees won't exceed 2000.
 */

/*
// Employee info
class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};
*/

// Solution 2:
// First use hash map to create mapping between id and employee object.
// Then use DFS to find all subordinates and sum up importance value
//
// Time: O(n)
// Space: O(n)
// 03/05/2018

class Solution {
    public HashMap<Integer, Employee> map;
    
    public int getImportance(List<Employee> employees, int id) {
        map = new HashMap<>();
        for (Employee emp : employees) {
            map.put(emp.id, emp);
        }
        return computeImportance(map.get(id));
    }
    
    public int computeImportance(Employee emp) {
        int res = emp.importance;
        for (Integer s : emp.subordinates) {
            res += computeImportance(map.get(s));
        }
        return res;
    }
}

// Solution 1:
// First use hash map to create mapping between id and employee object.
// Then use BFS to find all subordinates and sum up importance value
//
// Time: O(n)
// Space: O(n)
// 03/05/2018

class Solution {
    public HashMap<Integer, Employee> map;
    
    public int getImportance(List<Employee> employees, int id) {
        map = new HashMap<>();
        for (Employee emp : employees) {
            map.put(emp.id, emp);
        }
        return computeImportance(map.get(id));
    }
    
    public int computeImportance(Employee emp) {
        int res = 0;
        Queue<Employee> queue = new LinkedList<>();
        queue.offer(emp);
        while (!queue.isEmpty()) {
            Employee e = queue.poll();
            res += e.importance;
            for (Integer sid : e.subordinates) {
                queue.offer(map.get(sid));
            }
        }
        return res;
    }
}