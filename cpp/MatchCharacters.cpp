#include<iostream>
#include<vector>

using namespace std;

int matchCharacters(string s); 

int main() {
    string s1 = "ABba";
    string s2 = "ABbCca";
    string s3 = "ABbba";

    cout << s1 << "-->" << matchCharacters(s1) << endl;
    cout << s2 << "-->" << matchCharacters(s2) << endl;
    cout << s3 << "-->" << matchCharacters(s3) << endl;
}

int matchCharacters(string s) {
    vector<char> stack;
    int res = -1;
    for (int i = 0; i < s.length(); i++) {
	if (isupper(s[i])) {
	    stack.push_back(s[i]);
	} else {
	    if (stack.size() == 0) {
		break;	
	    } else {
		if (stack.back() != toupper(s[i])) {
		    break;
		}
		stack.pop_back();
	    }
	}
	res = i;
    }
    return res;
}
