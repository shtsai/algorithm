/*
    Design and implement a TwoSum class. It should support the following operations: add and find.

    add - Add the number to an internal data structure.
    find - Find if there exists any pair of numbers which sum is equal to the value.

    For example,
    add(1); add(3); add(5);
    find(4) -> true
    find(7) -> false
 */

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */

// Solution 2: HashMap
// O(1) insertion, O(n) look up
// O(n) space
// Good when more insertions than look ups
// 09/28/2017

class TwoSum {
    HashMap<Integer, Integer> map;
    /** Initialize your data structure here. */
    public TwoSum() {
        map = new HashMap<>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        if (map.containsKey(number)) {
            map.put(number, map.get(number)+1);
        } else {
            map.put(number, 1);
        }
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for (int key : map.keySet()) {
            if (key == value-key) {
                if (map.get(key) >= 2) {
                    return true;
                }
            } else if (map.containsKey(value-key)) {
                return true;
            }
        }
        return false;
    }
}

// Solution 1: Use HashSet to store all two sums
// O(n) insertion - pair new number with all existing numbers
// O(1) look up - see if the set contains the target sum
// O(n^2) space - for all possible pairs
// Good when more look ups than insertions
// 09/28/2017

class TwoSum {
    HashSet<Integer> sums;
    ArrayList<Integer> nums;
    /** Initialize your data structure here. */
    public TwoSum() {
        sums = new HashSet<Integer>();
        nums = new ArrayList<Integer>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        for (int i = 0; i < nums.size(); i++) {
            sums.add(number + nums.get(i));
        }
        nums.add(number);
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        return sums.contains(value);
    }
}
