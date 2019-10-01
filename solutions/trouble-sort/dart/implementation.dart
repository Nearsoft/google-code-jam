import 'dart:io';
import 'dart:math' as math;

main() {
  String stringCase;
  //List<String> results =[];
  int numberOfCases = int.parse(stdin.readLineSync());
  stdout.flush();
  BytesBuilder builder = new BytesBuilder();
  for (int i = 1; i <= numberOfCases; i++) {
      int size = int.parse(stdin.readLineSync());
      List<int> lint = new List(size);
      for (int j = 0; j < size; j++) {
        int char = stdin.readByteSync();
        while (char >= 48 && char <= 57) {
          builder.addByte(char);
          char = stdin.readByteSync();
        }
        lint[j] = int.parse(String.fromCharCodes(builder.takeBytes()));
      }
      //print(lint);
      if(lint.length > 1 && lint.length <= math.pow(10, 9)){
   print("Case #${i}: ${separateArray(lint)}");
      stdout.flush();
  }
     
  }
  
  return 0;
}

separateArray(array){

  List<int> odds = new List((array.length / 2).floor());
  List<int> evens= new List(array.length - odds.length);
  
  int m, n;
  m = 0;
  n = 0;
  for(var i = 0; i<array.length; i++){
    if(i%2==0){
     evens[n] = array[i];
     n++;
    }
    if(i%2!=0){
      odds[m] = array[i];
      m++;
    }
  }
  evens = evens..sort();
  odds = odds..sort();
  var j=0,k=0;
  for(var i=0; i<array.length-1;i++){
    if(i%2==0){
      if (evens[j] > odds[k]) {
          return i;
      }
      j++;
    }else{
      if (odds[k] > evens[j]) {
        return i;
      }
      k++; 
    }
  }
  return "OK";
}
