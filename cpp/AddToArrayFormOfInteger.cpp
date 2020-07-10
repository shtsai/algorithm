/*
	For a non-negative integer X, the array-form of X is an array of its digits in left to right order.  
	For example, if X = 1231, then the array form is [1,2,3,1].

	Given the array-form A of a non-negative integer X, return the array-form of the integer X+K.

	1. 1 <= A.length <= 10000
	2. 0 <= A[i] <= 9
	3. 0 <= K <= 10000
	4. If A.length > 1, then A[0] != 0
 */

// Solution 1:
class Solution {
public:
    vector<int> addToArrayForm(vector<int>& A, int K) {
        // Convert K to array form
        auto reverseArrayFormK = intToReverseArrayForm(K);
        
        // Add A to K
        auto reverseArrayFormA = reverse(A);
        vector<int> sumArray;
        for (int i = 0; i < reverseArrayFormK.size() || i < reverseArrayFormA.size(); ++i) {
            const int elementA = i < reverseArrayFormA.size() ? reverseArrayFormA[i] : 0;
            const int elementK = i < reverseArrayFormK.size() ? reverseArrayFormK[i] : 0;
            sumArray.emplace_back(elementA + elementK);
        }
        
        // Handle carry
        int carry = 0;
        for (int i = 0; i < sumArray.size(); ++i) {
            sumArray[i] += carry;
            carry = sumArray[i] / 10;
            sumArray[i] %= 10;
        }
        if (carry != 0) {
            sumArray.emplace_back(carry);
        }
        
        return reverse(sumArray);
    }   

private:
    vector<int> intToReverseArrayForm(int K) {
        vector<int> out;
        while (K > 0) {
            out.emplace_back(K % 10);
            K = K / 10;
        }
        return out;
    }
    
    vector<int> reverse(const vector<int>& arr) {
        vector<int> out;
        out.reserve(arr.size());
        for (int i = arr.size() - 1; i >= 0; i--) {
            out.emplace_back(arr[i]);
        }
        return out;
    }
 
};

// Solution 2:
// Treat entire K as the carry.

class Solution {
public:
    vector<int> addToArrayForm(vector<int>& A, int K) {
        vector<int> out;
        int i = A.size() - 1;
        int carry = K;
        
        while (i >= 0 || carry != 0) {
            int v = i >= 0 ? A[i] + carry : carry;
            out.emplace_back(v % 10);
            carry = v / 10;
            --i;
        }
        
        std::reverse(out.begin(), out.end());
        return out;
    }   
};

