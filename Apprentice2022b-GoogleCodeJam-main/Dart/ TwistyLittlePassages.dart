import 'dart:io';

dynamic solve() {
  String temporal = stdin.readLineSync()!;
  List splitted = temporal.split(' ');

  int N = int.parse(splitted[0]);
  int K = int.parse(splitted[1]);

  String otemporal = stdin.readLineSync()!;
  List osplitted = otemporal.split(' ');

  int R = int.parse(osplitted[0]);
  int P = int.parse(osplitted[1]);

  Set<int> roomsLeft = {};

  for (int i = 1; i < N + 1; i++) {
    roomsLeft.add(i);
  }

  if (roomsLeft.contains(R)) {
    roomsLeft.remove(R);
  }

  int degree = P;
  num degreeRl = degree;
  num countR1 = 1;
  int iterCounter = 0;

  for (int i = 0; i < K; i++) {
    if (i % 2 == 0) {
      print("W");
      otemporal = stdin.readLineSync()!;
      osplitted = otemporal.split(' ');

      R = int.parse(osplitted[0]);
      P = int.parse(osplitted[1]);
    } else {
      print("T ${roomsLeft.elementAt(iterCounter)}");
      otemporal = stdin.readLineSync()!;
      osplitted = otemporal.split(' ');

      R = int.parse(osplitted[0]);
      P = int.parse(osplitted[1]);

      degreeRl += P;
      countR1 += 1;
      iterCounter += 1;
    }
    if (roomsLeft.contains(R)) {
      roomsLeft.remove(R);
      degree += P;
    }
  }

  num degreeAvg = degreeRl / countR1;
  num result = (degree + degreeAvg * roomsLeft.length) / 2;
  print("E ${result.round()}");
}

void main() {
  int cases = int.parse(stdin.readLineSync()!);

  for (int i = 0; i < cases; i++) {
    solve();
  }
}
