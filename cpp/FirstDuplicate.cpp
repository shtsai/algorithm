/*
 * Given a string, return the first duplicate character.
 */

#include<iostream>
#include<unordered_map>

using namespace std;

char findFirstRepeatingChar(string input);

int main() {
    string input;
    cin >> input;
    cout << findFirstRepeatingChar(input) << endl;
}

char findFirstRepeatingChar(string input) {
    unordered_map<char, int> umap;
    for (int i = 0; i < input.size(); i++) {
	auto it = umap.find(input[i]); 
	if (it == umap.end()) {
	    umap.insert(make_pair<char, int>(input[i], 1));
	} else {
	    it->second = it->second + 1;
	}
    }
    for (int i = 0; i < input.size(); i++) {
	auto it = umap.find(input[i]);
	if (it->second > 1) {
	    return it->first;
	}
    }
    return ' ';
}
