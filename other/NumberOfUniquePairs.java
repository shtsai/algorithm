/*
    Two Sum - uniqle Pairs
    Complete the function below.
    a = [1,2,4,5,3,2,4,3]; k = 6 => 2

    should return 3 ([1,5], [2,4], [3,3])

    Input is not sorted.
    -- Bloomberg
 */

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class NumberOfUniquePairs {
    public static int numberOfUniquePairs(int[] a, long k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n : a) {   // create frequency mapping
            if (!map.containsKey(n)) {
                map.put(n, 1);
            } else {
                map.put(n, map.get(n)+1);
            }
        }
        
        int res = 0;
        Set<Integer> keys = new HashSet<>();
        for (int i : map.keySet()) {
            keys.add(i);
        }
        for (int key : keys) {
            if (map.containsKey((int) k - key)) {
                if (key == k - key && map.get(key) < 2) {
                    continue;
                } 
                res++;
                map.remove(key);
                map.remove(k-key);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] input = {1,2,4,5,3,2,4,3};
        long k = Long.parseLong(args[0]);
        System.out.println(numberOfUniquePairs(input, k));
    }
}
