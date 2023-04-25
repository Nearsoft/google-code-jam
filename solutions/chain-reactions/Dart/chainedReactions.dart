import 'dart:io';
import 'dart:math';

void main() {
  // Reading the number of tests from input
  int testCases = int.parse(stdin.readLineSync() ?? '0');

  for (int i = 0; i < testCases; i++) {
    // Reading the number of modules
    int nModules = int.parse(stdin.readLineSync() ?? '0');

    // Reading module fun
    List<int> fun = stdin.readLineSync()?.split(' ').map(int.parse).toList() ?? [];
    fun.insert(0, 0);

    // Reading module pointers
    List<int> modules = stdin.readLineSync()?.split(' ').map(int.parse).toList() ?? [];

    // Encode the graph adjacencies
    List<List<int>> connections = List.generate(nModules, (_) => []);
    for (int j = 0; j < nModules; j++) {
      connections[modules[j]].add(j + 1);
    }

    int result = 0;

    // Start DFS from inverted graph
    for (int v = nModules - 1; v >= 0; v--) {
      if (connections[v].isEmpty) {
        continue;
      }

      int minFun(int a, int b) => (fun[a] < fun[b]) ? a : b;
      int u = connections[v].reduce(minFun);

      // Update fun
      fun[v] = max(fun[v], fun[u]);

      // Filter out the element 'u' from connections[v]
      List<int> filteredNeighbors = connections[v].where((neighbor) => neighbor != u).toList();

      // Calculate the sum of the values from fun for each filtered neighbor
      int sumOfValues = filteredNeighbors.fold(0, (accumulator, neighbor) => accumulator + fun[neighbor]);

      // Update the result by adding the sum of values
      result += sumOfValues;
    }

    result += fun[0];

    stdout.write("Case #${i + 1}: ${result}\n");
  }
}
