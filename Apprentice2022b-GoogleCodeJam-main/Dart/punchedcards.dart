import 'dart:io'; //For user input

void main () {


  // Read number of cases from user input
  int cases = int.parse(stdin.readLineSync());
  

  //Create an empty array where the case answers will be stored
  List<List> casesArray = [];
  
  // Add coords of each case to the array
  for (int i = 0; i < cases ; i ++) {
    String coordss = (stdin.readLineSync());
    var coords = coordss.split(' ');
    int x = int.parse(coords[0]);
    int y = int.parse(coords[1]);
    //print('coords $x, $y');
    casesArray.add([x, y]);
  }
  //print(casesArray);

  // Print case number and solved punched card
  for (int i = 0; i < cases ; i ++) {
    print('Case #${i+1}:');
    printPunchCard(casesArray[i][0], casesArray[i][1]);
  }
  
}

// Solve each case
printPunchCard (int rows, int cols) {
/*
int row: number of rows of the punched card
int col: number of columns of the punched card

returns: nothing, only prints
*/ 

  // Top left print
  String top_left_plus = "..";

  for (int i = 0; i < cols - 1 ; i ++) {
    top_left_plus += "+-";
  }
  top_left_plus += "+";
  
  print(top_left_plus);

  String top_left_bars = "..";

  for (int i = 0; i < cols - 1 ; i ++) {
    top_left_bars += "|.";
  }
  top_left_bars += "|";
  
  print(top_left_bars);
 // End of top left print

  // Constructing the string to print multiple times
  String plus = "";
  String bars = "";

  // Plus sign string
  for (int i = 0; i < cols ; i ++) {
    plus += "+-";
  }
  plus += "+";

  // Bar sign string
  for (int i = 0; i < cols ; i ++) {
    bars += "|.";
  }
  bars += "|";

  // Printing rest of the punch card
  for (int i = 0; i < rows ; i ++) {
    print(plus);
    if (i == rows - 1) {
      continue;
    } else {
      print(bars);
    }
  }

}