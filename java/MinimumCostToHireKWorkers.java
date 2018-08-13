/*
	There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].
	Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers, we must pay them according to the following rules:
	Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
	Every worker in the paid group must be paid at least their minimum wage expectation.
	Return the least amount of money needed to form a paid group satisfying the above conditions.

	Example 1:
	Input: quality = [10,20,5], wage = [70,50,30], K = 2
	Output: 105.00000
	Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
	
	Example 2:
	Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
	Output: 30.66667
	Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately. 
	
	Note:
	1 <= K <= N <= 10000, where N = quality.length = wage.length
	1 <= quality[i] <= 10000
	1 <= wage[i] <= 10000
	Answers within 10^-5 of the correct answer will be considered correct.
 */

// Solution 2:
// Sort workers by increasing rate.
// Then find K workers with minimum quality * rate.
//
// Time: O(nlogn)
// Space: O(n)
// 08/12/2018
class Solution {
    private class Worker implements Comparable<Worker>{
        int wage;
        int quality;
        double rate;
        public Worker(int quality, int wage) {
            this.quality = quality;
            this.wage = wage;
            this.rate = (double) wage / quality;
        }
        public int compareTo(Worker other) {
            if (this.rate < other.rate) {
                return -1;
            } else {
                return 1;
            }
        }
    }
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        PriorityQueue<Worker> pq = new PriorityQueue<>();
        for (int i = 0; i < quality.length; i++) {
            pq.offer(new Worker(quality[i], wage[i]));
        }
        
        PriorityQueue<Integer> qualityHeap = new PriorityQueue<>(Collections.reverseOrder());
        double min = Double.MAX_VALUE;
        int qualitySum = 0;
        while (!pq.isEmpty()) {
            Worker worker = pq.poll();
            qualitySum += worker.quality;
            qualityHeap.offer(worker.quality);
            if (qualityHeap.size() > K) {
                qualitySum -= qualityHeap.poll();
            }
            if (qualityHeap.size() == K) {
                min = Math.min(min, qualitySum * worker.rate);
            }
        }
        return min;
    }
}

// Solution 1: 
// Try different minimum salary.
// Then get minimum sum of k person.
// 
// Time: O(n^2 * logn) - Time limit exceeded
// Space: O(n)
// 08/12/2018

class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < quality.length; i++) {
            int q = quality[i];
            int w = wage[i];
            double rate = (double) w / q;
            PriorityQueue<Double> costs = new PriorityQueue<>();
            for (int j = 0; j < quality.length; j++) {
                if (j == i) {
                    continue;
                }
                if (rate * quality[j] >= wage[j]) {
                    costs.offer(rate * quality[j]);
                }
            } 
            double sum = w;
            if (costs.size() >=  K - 1) {
                for (int j = 0; j < K - 1; j++) {
                    sum += costs.poll();
                }
                min = Math.min(min, sum);
            }
        }
        return min;
    }
}