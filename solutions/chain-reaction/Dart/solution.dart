import 'dart:io'; //For user input
import 'dart:math'; // For reducer method

void main () {
  // Read number of cases from user input
  int cases = int.parse(stdin.readLineSync());
  
  for (int i = 0; i < cases; i++) {
        print("Case #${i+1}: " + chain().toString());
    }

}

chain () {
  int n = int.parse(stdin.readLineSync()); // Number of nodes
  List<int> funN = [0];
  funN.addAll(stdin.readLineSync().split(" ").map(int.parse).toList());  // Fun numbers mapped to Arr offset 1 so that pointers are correct
  List<int> ptrN = [0];
  ptrN.addAll(stdin.readLineSync().split(" ").map(int.parse).toList()); // Pointer numbers mapped to Arr offset 1 so that pointers are correct
  
  const int inf = 9007199254740991; // Infinity variable
  List<int> temp = List.filled(n + 1, inf); // Temporal array used to modify data
  
  int total = funN.fold(0, (previous, current) => previous + current); // Total sum of Array

  for (int i = n; i > 0; i--){
    if (temp[i] == inf) { 
      temp[i] = 0; // We convert the unknown number to 0 for us to work with it
    }
    
    total -= min(funN[i], temp[i]); // on our first run we are not going to subtract anything since temp[i] = 0
    int funM = max(funN[i], temp[i]); // then we change our temp value
    
    temp[ptrN[i]] = min(temp[ptrN[i]], funM);
    
  }
  return total;
}