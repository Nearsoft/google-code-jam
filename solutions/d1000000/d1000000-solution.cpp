#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int main () {
   int t,n,cas=1;
   cin >> t;
   while (t--) {
       cin >> n;
       vector<int> s(n);
       for (int i = 0; i < n; i++) {
           cin >> s[i];
       }
       sort(s.begin(), s.end());
       int curr = 1;
       for (auto dice: s) {
           if (curr > dice) {
               continue;
           }
           curr++;
       }
       cout << "Case #" << cas++ << ": " << curr - 1 << endl;
   }
   return 0;
}
