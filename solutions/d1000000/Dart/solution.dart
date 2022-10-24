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
