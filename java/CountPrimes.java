/**
 * Count the number of prime numbers less than a non-negative number, n.
 */


public class Solution {
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
            for (int i = 2; i < n; i++) {
                isPrime[i] = true;
            }
   
        for (int i = 2; i * i < n; i++) {
            if (!isPrime[i]) continue;
            for (int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }
        
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) count++;
        }
        return count;
    }
    
    
    /*
    // method 1
    public int countPrimes(int n) {
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (isPrime(i)) {
                count += 1;
            }
        }
        
        return count;
    }
    
    public boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }

        for (int i = 2; i*i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        
        return true;
    }
    */
    
}