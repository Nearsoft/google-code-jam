import 'dart:io';
import 'dart:math';

List<num> powersOftwo(N) {
  List<num> A = [];

  for (int i = 0; i < 30; i++) {
    A.add(pow(2, i));
  }

  if (N <= 30) {
    return A;
  } else {
    for (int i = 0; i < N - 30; i++) {
      A.add(pow(10, 9) - i);
    }
    return A;
  }
}

solveSum(A, B, N) {
  List<int> equalSum = [];
  for (int i = 0; i < B.length; i++) {
    A.add(B[i]);
  }
  A.sort();
  num aSum = 0;
  num bSum = 0;

  for (int i = A.length - 1; i >= 0; i--) {
    if (aSum > bSum) {
      equalSum.add(A[i]);
      bSum += A[i];
    } else {
      aSum += A[i];
    }
  }
  return equalSum;
}

void main() {
  int T = int.parse(stdin.readLineSync());

  for (int i = 0; i < T; i++) {
    int N = int.parse(stdin.readLineSync());

    List<num> A = powersOftwo(N);

    List<String> temporal = A.map((e) => e.toString()).toList();
    print(temporal.join(" "));

    List<String> bString = stdin.readLineSync()!.split(" ");

    List<int> B = bString.map(int.parse).toList();

    List<num> C = solveSum(A, B, N);

    List<String> listC = C.map((e) => e.toString()).toList();
    print(listC.join(" "));
  }
}
