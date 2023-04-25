import 'dart:io';

void main(List<String> args) {
  int T = int.parse(args[0]);
  for (var i = 1; i <= T; i++) {
    print("Case #${i}:");
    solution(int.parse(args[(i*2)-1]), int.parse(args[i*2]));
  }
}

void solution(int R, int C) {
  for (var j = 0; j <= R * 2; j++) {
    bool isEvenJ = j % 2 == 0;
    for (var i = 0; i <= C * 2; i++) {
      bool isEvenI = i % 2 == 0;

      (i < 2 && j < 2)
          ? stdout.write(".")
          : isEvenI && isEvenJ
              ? stdout.write("+")
              : isEvenI && !isEvenJ
                  ? stdout.write("|")
                  : !isEvenI && isEvenJ
                      ? stdout.write("-")
                      : stdout.write(".");
    }
    stdout.write("\n");
  }
}
