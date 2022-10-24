import 'dart:io';

void main() {
  int caseN = 0;
  int t = int.parse(stdin.readLineSync());
  for (int i = 0; t > i; i++) {
    int length = 0;
    int d = int.parse(stdin.readLineSync());
    String elements = int.parse(stdin.readLineSync());
    List dicesList = elements.split(" ").map((e) => int.parse(e)).toList();
    dicesList.sort();
    for (int i = 0; i < d; i++) {
      if (dicesList.elementAt(i) > length) {
        length += 1;
      }
    }
    print("Case #${i + 1}: $length");
  }
}

/*
import 'dart:io';

void main() {
    int t = int.parse(stdin.readLineSync());                                    // Read Cases
    for( int i = 0; t > i; i++ ) {                                              // For each print answer
        answer(i);                                                              // Answer format Case#x: x
    }
}

void answer(int i) {
    int length = 0;                                                             // Initiate answer
    int d = int.parse(stdin.readLineSync());                                    // Read number of dices
    String elements = stdin.readLineSync();                                     // Read array stdin
    List dicesList = elements.split(" ").map((e) => int.parse(e)).toList();     // Put the stdin in a List
    dicesList.sort();                                                           // Sort the List
    for(int i = 0; i < d; i++) {                                                // Iterate the List
        if(dicesList.elementAt(i) > length) {                                   // If the length is smaller than the element we are at
            length += 1;                                                        // Add +1 to length
        }                                                                       // Else do nothing
    }                                                                           // End of loop
    print("Case #${i + 1}: $length");                                           // return answer
}
*/

/*
import 'dart:io';

void main() {
    int cases = int.parse(stdin.readLineSync()!);

    List results = [];
    
    for(int i = 0; i < cases; i++) {
        results.add(calculateStraight());
    }
    
    for(int i = 0; i < cases; i++) {
        int nu = i + 1;
        int result = results.elementAt(i);
        print("Case #$nu: $result");
    }
}

int calculateStraight() {
    int dices = int.parse(stdin.readLineSync()!);
    
    List dicesList = [];
    String elements = stdin.readLineSync()!;
    List temp = elements.split(" ");
    for(int i = 0; i < dices; i ++) {
        dicesList.add(int.parse(temp.elementAt(i)));
    }
    dicesList.sort();
    
    int total = 0;
    
    for(int i = 0; i < dicesList.length; i++){
        if(total < dicesList.elementAt(i)){
            total += 1;
        } else { continue; }
    }
    
    return total;
}
*/