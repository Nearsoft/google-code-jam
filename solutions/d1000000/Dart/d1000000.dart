// this is the suggested approach in the analysis page
// as is this code gives the correct answer but exceeds the time limit
// last updated Wed 19 Apr 15:00

// dart:io needs to be imported to read inputs
import 'dart:io';

// this function starts at 1 and counts to the highest number possible
// the highest number possible is also the length of the longest straight
int maxStraightLength(list) {
  var maxLength = 0;
  list.sort();

  for (int j = 0; j < list.length; j++){
    if (list[j] > maxLength){
      maxLength++;
    }
  }
  return maxLength;
}

void main() {
  // reading number of tests from input
  var testS = stdin.readLineSync();
  int testCases = testS != null ? int.parse(testS) : 0;

  for (int i = 0; i < testCases; i++) {
    // reading number of dice and their value
    var nDiceS = stdin.readLineSync();
    int nDice = nDiceS != null ? int.parse(nDiceS) : 0;
    var diceIn = stdin.readLineSync();

    // converting string to list of strings
    List<String> diceS = diceIn != null ? diceIn.split(' ') : [];
    // converting list of strings to list of numbers
    List<int> dice = diceS.map(int.parse).toList();

    int res = maxStraightLength(dice);
    print("Case #${i + 1}: $res");
  }
}