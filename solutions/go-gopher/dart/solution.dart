import 'dart:io';

main() {
  int t = int.parse(stdin.readLineSync()); // Get it from codejam
  for (int i = 1; i <= t; i++) {
    int a = int.parse(stdin.readLineSync()); // Get it from codejam
    List dimentions = findSquareDimentions(a);

    // Create a map of the places where we can deploy the gopher
    // and assign them a starting scores of the number of squares
    // that haven't been marked yet (9)
    // For example, for a = 16 we would get the dimensions of x=4 and y=4
    //   1 2 3 4 5 6
    // 1|0 0 0 0 0 0
    // 2|0 x x x 0 0
    // 3|0 x x x 0 0
    // 4|0 0 0 0 0 0
    // 5|0 0 0 0 0 0

    // the places where 'x' it's the only places we would want to shoot, notice how the 0's
    // surrounding the x's form the 4x4 square that we want to fill up
    int max_x = dimentions[0];
    int max_y = dimentions[1];
    Map<List<int>, int> targets = new Map();
    for (int i = 2; i < max_x; i++) {
      for (int j = 2; j < max_y; j++) {
        targets[[i, j]] = 9;
      }
    }
    // Make a list for the places that are already mark so we don't count them twice

    List<List<int>> alreadyHit = [];
    int x = -1;
    int y = -1;

    // Start iterating, shooting and updating the map, until we get 0 0 which signals the end of the test case
    while (!(x == 0 && y == 0)) {
      List<int> target = getBestTarget(targets);

      // Deploy the gopher
      print(target[0].toString() + " " + target[1].toString()); // Send it to codejam
      String hit = stdin.readLineSync(); // Get it from codejam
      x = int.parse(hit.split(" ")[0]);
      y = int.parse(hit.split(" ")[1]);
      if (!containsPair(alreadyHit, [x, y])) {
        updateTargets(targets, x, y);
        alreadyHit.add([x, y]);
      }
    }
  }
}

// Finds the optimal dimentions for a rectangle that can fit 'a' inside
List<int> findSquareDimentions(int a) {
  int smallestDifference = a;
  int x = 0;
  int y = 0;
  for (var i = 1; i < a / 2; i++) {
    if (a % i == 0 && ((a/i).round() - i).abs() < smallestDifference) {
      smallestDifference = ((a/i).round() - i).abs();
      x = (a / i).round();
      y = i;
    }
  }
  if(x < 3 || y < 3){
    return findSquareDimentions(a+1);
  }
  return [x, y];
}

// Gets the best target from the map
List<int> getBestTarget(Map<List<int>, int> targets) {
  int highest_missing_squares = -1;
  List<int> best_target;
  for (MapEntry target in targets.entries) {
    if (target.value > highest_missing_squares) {
      highest_missing_squares = target.value;
      best_target = target.key;
    }
  }
  return best_target;
}

// Updates the targets based on a hit, if the point is adjacent to any of the targets it decreases 1 from it's value
// in other words, there is one less missing square from that location
updateTargets(Map<List, int> targets, int x, int y) {
  for (MapEntry target in targets.entries) {
    if ((target.key[0] - 1 <= x) &&
        (x <= target.key[0] + 1) &&
        (target.key[1] - 1 <= y) &&
        (y <= target.key[1] + 1)) {
      targets[target.key] = target.value - 1;
    }
  }
}

// Utility to check if the list contains a coordinate.
bool containsPair(List<List<int>> list, List<int> targetPair) {
  for (List<int> pair in list) {
    if (pair[0] == targetPair[0] && pair[1] == targetPair[1]) {
      return true;
    }
  }
  return false;
}