/**
    We have an array A of integers, and an array queries of queries.

    For the i-th query val = queries[i][0], index = queries[i][1], we add val to A[index].  Then, the answer to the i-th query is the sum of the even values of A.

    (Here, the given index = queries[i][1] is a 0-based index, and each query permanently modifies the array A.)

    Return the answer to all queries.  Your answer array should have answer[i] as the answer to the i-th query.

    Example 1:

    Input: A = [1,2,3,4], queries = [[1,0],[-3,1],[-4,0],[2,3]]
    Output: [8,6,2,4]
    Explanation: 
    At the beginning, the array is [1,2,3,4].
    After adding 1 to A[0], the array is [2,2,3,4], and the sum of even values is 2 + 2 + 4 = 8.
    After adding -3 to A[1], the array is [2,-1,3,4], and the sum of even values is 2 + 4 = 6.
    After adding -4 to A[0], the array is [-2,-1,3,4], and the sum of even values is -2 + 4 = 2.
    After adding 2 to A[3], the array is [-2,-1,3,6], and the sum of even values is -2 + 6 = 4.
    
    Note:

    1 <= A.length <= 10000
    -10000 <= A[i] <= 10000
    1 <= queries.length <= 10000
    -10000 <= queries[i][0] <= 10000
    0 <= queries[i][1] < A.length
 */

#include <vector>

using namespace std;

class Solution {
public:
    vector<int> sumEvenAfterQueries(vector<int>& A, vector<vector<int>>& queries) {
        vector<int> res;
        res.reserve(queries.size());
        
        int sum = -1;
        
        for (int i = 0; i < queries.size(); ++i) {
            const auto query = queries[i];
            const int val = query[0];
            const int index = query[1];
            
            int oldValue = A[index];
            A[index] += val;
            
            
            // First iteration, need summation
            if (i == 0) {
                sum = sumEvenNumbers(A);   
            } else {
               if (oldValue % 2 == 0) {
                   sum -= oldValue;
               } 
               if (A[index] % 2 == 0) {
                   sum += A[index];
               }
            }
            
            res.emplace_back(sum);
        }
        
        return res;
    }
    
    int sumEvenNumbers(const vector<int>& A) {
        int sum = 0;
        for (auto i : A) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }
};

// Solution 1: Straightforward, perform summation after each update
// Time: O(mn), m = len(A) and n = len(queries)  
// Space: O(1)

class Solution {
public:
    vector<int> sumEvenAfterQueries(vector<int>& A, vector<vector<int>>& queries) {
        vector<int> res;
        res.reserve(queries.size());
        
        for (const auto& query : queries) {
            const int val = query[0];
            const int index = query[1];
            
            A[index] += val;
            
            res.emplace_back(sumEvenNumbers(A));
        }
        
        return res;
    }
    
    int sumEvenNumbers(const vector<int>& A) {
        int sum = 0;
        for (auto i : A) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;


    }
};