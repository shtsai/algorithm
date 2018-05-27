/*
    In LeetCode Store, there are some kinds of items to sell. Each item has a price.

    However, there are some special offers, and a special offer consists of one or more different kinds of items with a sale price.

    You are given the each item's price, a set of special offers, and the number we need to buy for each item. The job is to output the lowest price you have to pay for exactly certain items as given, where you could make optimal use of the special offers.

    Each special offer is represented in the form of an array, the last number represents the price you need to pay for this special offer, other numbers represents how many specific items you could get if you buy this offer.

    You could use any of special offers as many times as you want.

    Example 1:
    Input: [2,5], [[3,0,5],[1,2,10]], [3,2]
    Output: 14
    Explanation: 
    There are two kinds of items, A and B. Their prices are $2 and $5 respectively. 
    In special offer 1, you can pay $5 for 3A and 0B
    In special offer 2, you can pay $10 for 1A and 2B. 
    You need to buy 3A and 2B, so you may pay $10 for 1A and 2B (special offer #2), and $4 for 2A.
    Example 2:
    Input: [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
    Output: 11
    Explanation: 
    The price of A is $2, and $3 for B, $4 for C. 
    You may pay $4 for 1A and 1B, and $9 for 2A ,2B and 1C. 
    You need to buy 1A ,2B and 1C, so you may pay $4 for 1A and 1B (special offer #1), and $3 for 1B, $4 for 1C. 
    You cannot add more items, though only $9 for 2A ,2B and 1C.

    Note:
    There are at most 6 kinds of items, 100 special offers.
    For each item, you need to buy at most 6 of them.
    You are not allowed to buy more items than you want, even if that would lower the overall price.
 */

// Solution 2: Dynamic programming
// Similar to solution 1, but uses a memo array to avoid repeat computation.
// Since we do not mutate needs arrays, we can use them as keys to the hashmap.

class Solution {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        HashMap<List<Integer>, Integer> memo = new HashMap<>();
        return helper(price, special, needs, 0, memo);
    }
    
    public int helper(List<Integer> price, List<List<Integer>> special, List<Integer> needs, 
                      int cost, HashMap<List<Integer>, Integer> memo) {
        if (memo.containsKey(needs)) {    // already computed this case
            return memo.get(needs);
        }
        int status = satisfied(needs);
        if (status == -1) {
            return Integer.MAX_VALUE;
        } else if (status == 1) {
            return cost;
        } else {
            int res = cost;
            for (int i = 0; i < needs.size(); i++) { // buying with original price
                res += price.get(i) * needs.get(i);
            }
            for (List<Integer> s : special) {
                List<Integer> newNeeds = applyCoupon(s, needs);
                if (newNeeds != null) {
                    res = Math.min(res, helper(price, special, newNeeds, 
                                               cost + s.get(s.size()-1), memo));
                }
            }
            memo.put(needs, res);
            return res;
        }
    }
    
    // Check if the needs have been satisfied
    private int satisfied(List<Integer> needs) {
        for (Integer i : needs) {
            if (i < 0) {         // bought too many
                return -1;
            } else if (i > 0) {  // still need more
                return 0;
            }
        }
        return 1;   // all 0s, needs satisfied
    }
    
    // Apply coupon and generate new needs.
    // Returns null if the coupon cannot be applied.
    private List<Integer> applyCoupon(List<Integer> coupon, List<Integer> needs) {
        List<Integer> newNeeds = new ArrayList<>();
        for (int i = 0; i < needs.size(); i++) {
            int remain = needs.get(i) - coupon.get(i);
            if (remain < 0) {     // cannot apply coupon
                return null;
            } else {
                newNeeds.add(remain);
            }
        }
        return newNeeds;
    }
}


// Solution 1: DFS
// Try all possible ways to satisfy needs and find min cost.
// 
// 05/27/2018

class Solution {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return helper(price, special, needs, 0);
    }
    
    public int helper(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int cost) {
        int status = satisfied(needs);
        if (status == -1) {         // bought too many
            return Integer.MAX_VALUE;
        } else if (status == 1) {   // satisfied
            return cost;
        } else {
            int res = cost;
            for (int i = 0; i < needs.size(); i++) { // buying with original price
                res += price.get(i) * needs.get(i);
            }
            for (List<Integer> s : special) {   // try different coupons
                List<Integer> newNeeds = applyCoupon(s, needs);
                if (newNeeds != null) {
                    res = Math.min(res, helper(price, special, newNeeds, cost + s.get(s.size()-1)));
                }
            }
            return res;
        }
    }
    
    // Check if the needs have been satisfied
    private int satisfied(List<Integer> needs) {
        for (Integer i : needs) {
            if (i < 0) {         // bought too many
                return -1;
            } else if (i > 0) {  // still need more
                return 0;
            }
        }
        return 1;   // all 0s, needs satisfied
    }
    
    // Apply coupon and generate new needs.
    // Returns null if the coupon cannot be applied.
    private List<Integer> applyCoupon(List<Integer> coupon, List<Integer> needs) {
        List<Integer> newNeeds = new ArrayList<>();
        for (int i = 0; i < needs.size(); i++) {
            int remain = needs.get(i) - coupon.get(i);
            if (remain < 0) {     // cannot apply coupon
                return null;
            } else {
                newNeeds.add(remain);
            }
        }
        return newNeeds;
    }
}