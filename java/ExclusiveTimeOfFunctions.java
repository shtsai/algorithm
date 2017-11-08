/*
    Given the running logs of n functions that are executed in a nonpreemptive single threaded CPU, find the exclusive time of these functions.

    Each function has a unique id, start from 0 to n-1. A function may be called recursively or by another function.

    A log is a string has this format : function_id:start_or_end:timestamp. For example, "0:start:0" means function 0 starts from the very beginning of time 0. "0:end:0" means function 0 ends to the very end of time 0.

    Exclusive time of a function is defined as the time spent within this function, the time spent by calling other functions should not be considered as this function's exclusive time. You should return the exclusive time of each function sorted by their function id.

    Example 1:
    Input:
    n = 2
    logs = 
    ["0:start:0",
     "1:start:2",
     "1:end:5",
     "0:end:6"]
    Output:[3, 4]
    Explanation:
    Function 0 starts at time 0, then it executes 2 units of time and reaches the end of time 1. 
    Now function 0 calls function 1, function 1 starts at time 2, executes 4 units of time and end at time 5.
    Function 0 is running again at time 6, and also end at the time 6, thus executes 1 unit of time. 
    So function 0 totally execute 2 + 1 = 3 units of time, and function 1 totally execute 4 units of time.
    Note:
    Input logs will be sorted by timestamp, NOT log id.
    Your output should be sorted by function id, which means the 0th element of your output corresponds to the exclusive time of function 0.
    Two functions won't start or end at the same time.
    Functions could be called recursively, and will always end.
    1 <= n <= 100
 */

// Solution 1: Stack
// The functions are running in a LIFO manner, which can be represented using a stack.
// When a new function comes in, we put it onto the stack.
// If there is already something on the stack, we record the
// time previous function has been running.
// If a function is done, we can compute how long it has been running
// by subtracting the end timestamp and start timestamp.
// If there are other functions in the stack, we need to update its
// start time to the end time of the the current function.
// Note that we need to be careful with the timestamp,
// b/c in "start" log, timestamp is the very begining of that time.
// In "end" log, timestamp is the very end of that time.

// Time: O(n) - one pass
// Space: O(n) - one stack
// 11/08/2017
class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<String[]> stack = new Stack<>();
        for (String log : logs) {
            String[] current = log.split(":");
            if (current[1].equals("start")) {
                if (!stack.isEmpty()) {
                    String[] pre = stack.peek();
                    int preID = Integer.parseInt(pre[0]);
                    int runtime = Integer.parseInt(current[2]) - Integer.parseInt(pre[2]);
                    res[preID] += runtime;
                }
                stack.push(current);
            } else {
                String[] pre = stack.pop();
                int preID = Integer.parseInt(pre[0]);
                int runtime = Integer.parseInt(current[2]) - Integer.parseInt(pre[2]) + 1;
                res[preID] += runtime;
                
                if (!stack.isEmpty()) {
                    pre = stack.peek();  // update start timestamp of prepre
                    pre[2] = String.valueOf(Integer.parseInt(current[2]) + 1);
                }
            }
        }
        return res;
    }
}