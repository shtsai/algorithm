/*
    In an election, the i-th vote was cast for persons[i] at time times[i].

    Now, we would like to implement the following query function: TopVotedCandidate.q(int t) will return the number of the person that was leading the election at time t.  

    Votes cast at time t will count towards our query.  In the case of a tie, the most recent vote (among tied candidates) wins.

    Example 1:
    Input: ["TopVotedCandidate","q","q","q","q","q","q"], [[[0,1,1,0,0,1,0],[0,5,10,15,20,25,30]],[3],[12],[25],[15],[24],[8]]
    Output: [null,0,1,1,0,0,1]
    
    Explanation: 
    At time 3, the votes are [0], and 0 is leading.
    At time 12, the votes are [0,1,1], and 1 is leading.
    At time 25, the votes are [0,1,1,0,0,1], and 1 is leading (as ties go to the most recent vote.)
    This continues for 3 more queries at time 15, 24, and 8.
     
    Note:
    1 <= persons.length = times.length <= 5000
    0 <= persons[i] <= persons.length
    times is a strictly increasing array with all elements in [0, 10^9].
    TopVotedCandidate.q is called at most 10000 times per test case.
    TopVotedCandidate.q(int t) is always called with t >= times[0].
 */

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */

// Solution 2: Precompute winners + Binary Search
// Store the new winners at each time point.
// Then do binary search to find winner for the given time.
// Time: init - O(n)
//       q - O(logn)
// Space: O(n)
// 09/23/2018

class TopVotedCandidate {
    class Winner {
        int time;
        int winner;
        public Winner(int time, int winner) {
            this.time = time;
            this.winner = winner;
        }
    }
    
    List<Winner> winners;
    
    public TopVotedCandidate(int[] persons, int[] times) {
        winners = new ArrayList<>();
        int leader = -1;
        int leaderVote = 0;
        HashMap<Integer, Integer> votes = new HashMap<>();
        for (int i = 0; i < persons.length; i++) {
            int p = persons[i];
            int t = times[i];
            int v = votes.getOrDefault(p, 0) + 1;
            votes.put(p, v);
            
            if (v >= leaderVote) {
                winners.add(new Winner(t, p));
                leaderVote = v;
                leader = p;
            }
        }
    }
    
    public int q(int t) {
        int left = 0, right = winners.size() - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (winners.get(mid).time > t) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        if (winners.get(right).time <= t) {
            return winners.get(right).winner;
        } else {
            return winners.get(left).winner;
        }        
    }
}


// Solution 1: Linked List + HashMap
// Similar to LFU
// Store the top voted at each time point
// Time: init - O(n ^ 2)
//       q - O(logn)
// Space: O(n)
// 09/22/2018

class TopVotedCandidate {
    TreeMap<Integer, Integer> map;
    class Node {
        int freq;
        List<Integer> people;
        Node prev;
        Node next;
        public Node(int freq) {
            this.freq = freq;
            this.people = new ArrayList<>();
        }
    }
    
    public TopVotedCandidate(int[] persons, int[] times) {
        map = new TreeMap<>();
        
        HashMap<Integer, Node> nodes = new HashMap<>();
        Node head = new Node(1);
        Node tail = new Node(-1);
        head.next = tail;
        tail.prev = head;
            
        for (int i = 0; i < persons.length; i++) {
            int person = persons[i];
            if (!nodes.containsKey(person)) {
                head.people.add(person);
                nodes.put(person, head);
            } else {
                Node n = nodes.get(person);
                if (n.next.freq != n.freq + 1) {
                    Node nextNode = new Node(n.freq + 1);
                    nextNode.prev = n;
                    nextNode.next = n.next;
                    n.next = nextNode;
                    nextNode.next.prev = nextNode;
                }
                n.people.remove((Integer) person);
                n.next.people.add(person);
                nodes.put(person, n.next);
            }
            // store max for each time
            map.put(times[i], tail.prev.people.get(tail.prev.people.size() - 1));
        }
    }
    
    public int q(int t) {
        return map.get(map.floorKey(t));
    }
}


