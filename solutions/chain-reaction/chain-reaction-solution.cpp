#include <iostream>
#include <vector>
using namespace std;

int dfs(int curr_mod, vector<vector<int>>& mod, vector<bool>& vis, vector<int>& fun, long long& ans) {//DFS to traverse the graph
   	if (vis[curr_mod]) { //If the mod has been visited we don't need to process that node again
		return 0;
	} 
	vis[curr_mod] = true; //Mark the node as visited
	vector<int> children;
	int min_val = 1e9;
	long long sum = 0;
   	for (auto next_mod : mod[curr_mod]) {//Continue with the dfs
		int child = dfs(next_mod, mod, vis, fun, ans);
		children.push_back(child);
		min_val = min(min_val, child);
		sum += child;
	}
	if (children.size() == 0) { //Leaf node
		return fun[curr_mod];
	}
	ans += sum - min_val;
	return max(fun[curr_mod], min_val);
}

int main () {
	int t,n,p,cas=1;
	cin >> t; //Reads the number of testcases 't'
	while (t--) {
		cin >> n; //Reads the number of of modules 'n'
		vector<vector<int>> mod(n+1); //Adj list to store the graph
		vector<int> fun(n+1); //Contains F1,F2,F3,...,Fn
		vector<bool> vis(n+1,false); //Tells if mod[i] is visited or not
		for (int i = 1; i <= n; i++) { 
			cin >> fun[i]; //Reads F1,F2,F3,...,Fn
		}
		for (int i = 1; i <= n; i++) {//Builds graph
			cin >> p; //Reads P1,P2,P3,...,Pn
			mod[p].push_back(i);//Module p points to i
		}
		long long ans = 0;
		ans += dfs(0, mod, vis, fun, ans);
		cout << "Case #" << cas++ << ": " << ans << endl;
	}
	return 0;
}
