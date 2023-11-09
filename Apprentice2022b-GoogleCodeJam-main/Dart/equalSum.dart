import 'dart:io';
import 'dart:math';


void main(){
  run();
}

void run(){
  int cases = int.parse(stdin.readLineSync());
  for(int i = 0; i<cases;i++){
    int N = int.parse(stdin.readLineSync());
    if(N == -1){
      break;
    }

    List A = powersOfTwo(N);
    printList(A);

    String b = stdin.readLineSync();
    List B = fillWithInts(b.split(" "));
    printList(B);

    List C = solveSum(A,B);
    printList(C);
  }
}

List powersOfTwo(int N){
  List<num> a1 = [];
  for(int i = 0; i<30;i++){
    a1.add(pow(2,i));
  }
  int x = N-30;
  for(int i = 0; i<x;i++){
    a1.add(pow(10,9)-i);
  }
  return a1;
}

void printList(List x){
  for(int item in x){
    String x = "$item ";
    stdout.write(x);
  }
  print("");
}

List fillWithInts(List b){
  List n = [];
  for(String item in b){
    int x = int.parse(item);
    n.add(x);
  }
  return n;
}

List solveSum(List a, List b){
  List sl = [...a,...b]; //joined list
  List C = reverseList(sl);
  int aSum = 0;
  int bSum = 0;
  var equalSumSet = [];
  for(int c in C){
    if(aSum>bSum){
      equalSumSet.add(c);
      bSum = bSum + c;
    }
    else{
      aSum = aSum + c;
    }
  }

  return equalSumSet;
}

List reverseList(List x){
  List rl = [];
  for(int i = x.length-1; i>=0; i--){
    rl.add(x[i]);
  }
  return rl;
}