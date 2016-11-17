/*
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). 
 * You begin the journey with an empty tank at one of the gas stations.
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 *
 * The solution is guaranteed to be unique.
 */

public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        int[] net = new int[len];
        for (int i = 0; i < len; i++) {     
            net[i] = gas[i] - cost[i];      // use an array to store the net gas gain/loss
        }                                   // can skip this step to save space

        int start = len - 1;                // start from the last position (visited)
        int end = 0;                        // end at the first position (will visit)
        int currentGas = net[start];        // currentGas keeps track of current gas status
        while (end < start) {
            if (currentGas >= 0) {          // if currentGas >= 0, we still have enough gas to move to next station
                currentGas += net[end];     // update currentGas by adding the net gas gain/loss at this station
                end++;                      // move to next station
            } else {
                start--;                    // otherwise, we don't have enough gas, we need to move other start point back one station
                currentGas += net[start];   // to borrow some gas
            }
        }
        
        return currentGas >= 0 ? start : -1; // if eventually currentGas < 0, means there is no way to borrow enough gas
    }
}