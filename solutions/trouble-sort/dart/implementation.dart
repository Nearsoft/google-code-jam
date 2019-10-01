import 'dart:io';

main() {
  String stringCase;
  //List<String> results =[];
  int numberOfCases = int.parse(stdin.readLineSync());
  for (int i = 1; i <= numberOfCases; i++) {
  String size = stdin.readLineSync();
  String line = stdin.readLineSync();
  stringCase = line;
  var splitter = stringCase.split(" ");
  List<int> lint =splitter.map(int.parse).toList();
  //print(lint);
  separateArray(lint);
  print("Case #${i}: ${checkSorted(lint)}");
  }
  
}

separateArray(array){
  List<int> odds =[];
  List<int> evens=[]; 
  
  for(var i = 0; i<array.length; i++){
    if(i%2==0){
     evens.add(array[i]);
    }
    if(i%2!=0){
      odds.add(array[i]);
    }
  }
  evens = evens..sort();
  odds = odds..sort();
  var j=0,k=0;
  for(var i=0; i<array.length;i++){
    if(i%2==0){
      array[i]=evens[(j)];
      j++;
    }else{
      array[i]=odds[k];
      k++; 
    }
  }
  return array;
}

checkSorted(arraySorted){
  var len = arraySorted.length;
  var i=0;
  for(i; i<len -1;i++){
    if(arraySorted[i]>arraySorted[i+1]){
      return i ;
    }
  }
  return "OK";
}

