/*
 * Given a vector of departures time and a vector of arrival time,
 * determine the minimum number of gates required.
 */

#include<iostream>
#include<vector>

using namespace std;

int findMinGates(vector<int> arrivals, vector<int> departures, int flight);

int main() {
    vector<int> arrivals;
    arrivals.push_back(900);
    arrivals.push_back(940);
    arrivals.push_back(950);
    arrivals.push_back(1100);
    arrivals.push_back(1500);
    arrivals.push_back(1800);
    vector<int> departures;
    departures.push_back(910);
    departures.push_back(1200);
    departures.push_back(1120);
    departures.push_back(1130);
    departures.push_back(1900);
    departures.push_back(2000);
    
    int minGate = findMinGates(arrivals, departures, 6);
    cout << minGate << endl;
    return 0;
}

int findMinGates(vector<int> arrivals, vector<int> departures, int flight) {
    sort(arrivals.begin(), arrivals.end());
    sort(departures.begin(), departures.end());

    int i = 0, j = 0, gates = 0, maxGate = 0;
    while (i < flight && j < flight) {
	int iv = i == flight ? INT_MAX : arrivals[i];
	int jv = j == flight ? INT_MAX : departures[j];
	if (iv <= jv) {
	    gates++;
	    maxGate = max(maxGate, gates);
	    i++;
	} else {
	    gates--;
	    j++;
	}
    }

    return maxGate;
}
