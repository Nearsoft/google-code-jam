import 'dart:io';

dynamic calculateInk(List<List<int>> arr) {
  List<List<int>> printers = [arr[0], arr[1], arr[2]];
  int TOTAL = 1000000;
  int curr = 0;
  List<int> currPrint = [0, 0, 0, 0];

  for (int i = 0; i < 3; i++) {
    curr = printers[i].reduce((value, element) => value + element);
    if (curr < TOTAL) {
      return "IMPOSSIBLE";
    }
  }

  List<int> minInk = printers[0];
  for (int i = 0; i < 4; i++) {
    for (int j = 0; j < 3; j++) {
      if (minInk[i] > printers[j][i]) {
        minInk[i] = printers[j][i];
      }
    }
  }

  curr = minInk.reduce((value, element) => value + element);
  if (curr < 1000000) {
    return "IMPOSSIBLE";
  } else {
    int ink = 0;

    for (int i = 0; i < 4; i++) {
      if (ink + minInk[i] >= 1000000) {
        currPrint[i] = minInk[i] - (minInk[i] + ink - 1000000).abs();
        break;
      } else {
        ink = ink + minInk[i];
        currPrint[i] = minInk[i];
      }
    }
  }
  return currPrint;
}

void main() {
  int cases = int.parse(stdin.readLineSync());
  List<dynamic> results = List.filled(cases, 0);
  List<List<int>> caseArray = [[], [], []];

  for (int i = 0; i < cases; i++) {
    for (int j = 0; j < 3; j++) {
      dynamic temporal = stdin.readLineSync().split(" ");

      int c = int.parse(temporal[0]);
      int m = int.parse(temporal[1]);
      int y = int.parse(temporal[2]);
      int k = int.parse(temporal[3]);

      caseArray[j] = [c, m, y, k];
    }
    results[i] = calculateInk(caseArray);
  }

  // For loop for printing the results
  for (int i = 0; i < cases; i++) {
    if (results[i] == "IMPOSSIBLE") {
      print("Case #${i + 1}: ${results[i]}");
    } else {
      print("Case #${i + 1}: ${results[i].join(" ")}");
    }
  }
}
