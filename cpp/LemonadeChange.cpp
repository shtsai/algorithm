/*
	At a lemonade stand, each lemonade costs $5. 

	Customers are standing in a queue to buy from you, and order one at a time (in the order specified by bills).

	Each customer will only buy one lemonade and pay with either a $5, $10, or $20 bill.  You must provide the correct change to each customer, so that the net transaction is that the customer pays $5.

	Note that you don't have any change in hand at first.

	Return true if and only if you can provide every customer with correct change.
 */

// Solution 1:
// Keep track of available changes.
// Use a helper function to determine whether it is possible to make changes.
// 01/07/2019

class Solution {
private:
    const int deno[3] = {10, 5};
    
public:
    bool lemonadeChange(vector<int>& bills) {
        unordered_map<int, int> avail = {{10, 0}, {5, 0}};
        for (int bill : bills) {
            if (!canChange(avail, bill)) {
                return false;
            }
        }
        return true;
    }
    
    bool canChange(unordered_map<int, int>& avail, int target) {
        # add new bill to available
        avail[target] += 1;
        
        int need = target - 5;
        for (int d : deno) {
            while (d <= need && avail[d] > 0) {
                need -= d;
                avail[d] -= 1;
            }
        }
        
        return need == 0;
    }
};

// Solution 2: 
// Since we only have 3 kinds of bills, we can hard code valid ways of change.
// 01/07/2019

class Solution {    
public:
    bool lemonadeChange(vector<int>& bills) {
        unordered_map<int, int> avail = {{20, 0}, {10, 0}, {5, 0}};
        for (int bill : bills) {
            avail[bill] += 1;
            if (bill == 10) {
                if (avail[5] > 0) {
                    avail[5] -= 1;
                } else {
                    return false;
                }
            } else if (bill == 20) {
                if (avail[10] >= 1 && avail[5] >= 1) {
                    avail[10] -= 1;
                    avail[5] -= 1;
                } else if (avail[5] >= 3) {
                    avail[5] -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
};