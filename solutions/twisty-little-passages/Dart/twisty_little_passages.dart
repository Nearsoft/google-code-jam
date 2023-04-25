import 'dart:io';
import 'dart:math';

void firstTry() {
  int testCases = int.parse(stdin.readLineSync()!);

  for (var i = 0; i < testCases; i++) {
    List<String> inputList = stdin.readLineSync()!.split(' ');
    int nu_rooms = int.parse(inputList[0]);
    int nu_actions = int.parse(inputList[1]);

    var rooms_not_seen = List.generate(nu_rooms, (index) => index + 1);
    var nu_passages_seen = [];

    inputList = stdin.readLineSync()!.split(' ');
    int current_room = int.parse(inputList[0]);
    int nu_passages = int.parse(inputList[1]);

    // Borramos el cuarto ya visto y adjuntamos su número de pasillos.
    rooms_not_seen.removeAt(current_room - 1);
    nu_passages_seen.add(nu_passages);

    // Revolvermos los cuartos y tomamos K cuartos a teletransportarnos.
    rooms_not_seen.shuffle();
    rooms_not_seen =
        rooms_not_seen.sublist(0, min(nu_actions, rooms_not_seen.length));

    for (var room in rooms_not_seen) {
      stdout.write("T $room");

      inputList = stdin.readLineSync()!.split(' ');
      current_room = int.parse(inputList[0]);
      nu_passages = int.parse(inputList[1]);

      nu_passages_seen.add(nu_passages);
    }
    // Sacamos el promedio de los pasillos y lo multiplicamos por el
    // numero real de cuartos y dividimos entre 2, pues cada pasillo
    // lo contamos 2 veces.

    var result =
        nu_rooms * nu_passages_seen.reduce((value, element) => value + element);
    result = result / (2 * nu_passages_seen.length);
    stdout.write("E $result");
  }
}

void main() {
  firstTry();
}
