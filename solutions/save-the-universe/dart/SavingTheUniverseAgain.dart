// Dart implementation of 'Saving the universe, again'
import 'dart:math';

import 'dart:io';

solve_case(String shieldAndRobotProgram) {
  RegExp exp = new RegExp(r"(^\d+ [S|C]+$)");
  //const validator = /^\d+ [S|C]+$/;

  if (!(exp.hasMatch(shieldAndRobotProgram))) {
    print("Alien's program has an incorrect format");
    return -1;
  }
  var d = int.parse(shieldAndRobotProgram.split(' ')[0]);

  if (d < 1 || d > pow(10, 9)) {
    print("Shield's defence too high");
    return -1;
  }

  var p = shieldAndRobotProgram.split(' ')[1];
  if (p.length < 2 || p.length > 30) {
    print("Alien's program too long");
    return -1;
  }

  var letters = p.split('');
  var hacks = 0;
  var damage = 0;
  do {
    damage = damage_calc(letters);
    if (damage <= d) {
      return hacks;
    }
    hacks++;
  } while (hack(letters));

  return 'IMPOSSIBLE';
}

int damage_calc(List<String> robotProgram) {
  int total_damage = 0;
  var damage = 1;

  for (var i = 0; i < robotProgram.length; i++) {
    if (robotProgram[i] == 'S') {
      total_damage += damage;
    } else {
      damage *= 2;
    }
  }
  return total_damage;
}

bool hack(List<String> program) {
  for (var i = program.length - 1; i > 0; i--) {
    if (program[i] == 'S' && program[i - 1] == 'C') {
      var aux = program[i];
      program[i] = program[i - 1];
      program[i - 1] = aux;
      return true;
    }
  }
  return false;
}

main() {
  int numberOfCases = int.parse(stdin.readLineSync());
  for (int i = 1; i <= numberOfCases; i++) {
    String line = stdin.readLineSync();
    print("CASE #$i: ${solve_case(line)}");
  }
}
