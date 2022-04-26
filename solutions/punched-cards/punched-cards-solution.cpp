#include <iostream>
using namespace std;

int main () {
    int t,r,c,cas=1;
    cin >> t;
    while (t--) {
        cin >> r >> c;
        cout << "Case #" << cas++ << ":" << endl;
        for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (i == 0 && j == 0) {
					cout << "..";
					continue;
				}
				cout << "+-";
			}
			cout << "+" << endl;
			for (int j = 0; j < c; j++) {
				if (i == 0 && j == 0) {
					cout << "..";
					continue;
				}
				cout << "|.";
			}
			cout << "|" << endl;
			if (i == r - 1) {
				for (int j = 0; j < c; j++) {
					cout << "+-";
				}
				cout << "+" << endl;
			}
        }
    }
    return 0;
}
