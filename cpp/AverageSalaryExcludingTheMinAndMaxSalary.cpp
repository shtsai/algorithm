/*
	Given an array of unique integers salary where salary[i] is the salary of the employee i.

	Return the average salary of employees excluding the minimum and maximum salary.
 */

#include <limits> 

class Solution {
public:
    double average(vector<int>& salary) {
        int min = std::numeric_limits<int>::max();
        int max = std::numeric_limits<int>::min();
        int sum = 0;
        
        for (int s : salary) {
            sum += s;
            min = std::min(min, s);
            max = std::max(max, s);
        }
        
        sum = sum - max - min;
        int len = salary.size() - 2;
        return 1.0 * sum / len;
    }
};
