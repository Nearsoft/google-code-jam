import 'dart:io';

dynamic solve(INF) {
  dynamic temporal = stdin.readLineSync().split(" ");
  int e = int.parse(temporal[0]);
  int w = int.parse(temporal[1]);
  List<dynamic> allExcercises = [];
  List<dynamic> dp = [];

  for (int i = 0; i < e; i++) {
    allExcercises.add(stdin.readLineSync().split(" ").map(int.parse).toList());
  }

  for (int i = 0; i < e; i++) {
    dp.add([]);
    for (int j = 0; j < e; j++) {
      dp[i].add(0);
    }
  }

  List<dynamic> current = [];

  for (int i = 0; i < e; i++) {
    current = [];
    for (int l = 0; l < w; l++) {
      current.add(INF);
    }
    for (int j = i; j < e; j++) {
      for (int k = 0; k < w; k++) {
        if (allExcercises[j][k] < current[k]) {
          current[k] = allExcercises[j][k];
        }
      }
      dp[i][j] = current.reduce((value, element) => value + element);
    }
  }
  List dpDouble = [];

  for (int i = 0; i < e; i++) {
    dpDouble.add([]);
    for (int j = 0; j < e; j++) {
      dpDouble[i].add(INF);
    }
  }
  for (int row = 0; row < e; row++) {
    dpDouble[row][row] = 2 * dp[row][row];

    for (int revRow = row; revRow >= 0; revRow--) {
      for (int k = revRow; k < row; k++) {
        int minval;
        for (int min = revRow; min <row; min++){
          if (min == revRow){
            minval = dpDouble[revRow][min]+dpDouble[min+1][row]-2*dp[revRow][row];
          } else{
            int curr = dpDouble[revRow][min]+dpDouble[min+1][row]-2*dp[revRow][row];
            if (curr < minval) {
              minval = curr;
            }
          }          
        }
        dpDouble[revRow][row] = minval;
      }
    }
  }
  return dpDouble[0][e-1];
}

void main() {
  num INF = (999999999999999999);

  int cases = int.parse(stdin.readLineSync());

  List resultsArray = [];

  for (int i = 0; i < cases; i++) {
    resultsArray.add(solve(INF));
  }
  for (int i = 0; i < cases; i++) {
    print("Case #${i + 1}: ${resultsArray[i]}");
  }
}