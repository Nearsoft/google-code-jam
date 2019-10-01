import 'dart:io';
import 'dart:convert';
main() {
  String stringCase;
  List<String> results =[];
  int numberOfCases = int.parse(stdin.readLineSync());
  for (int i = 1; i <= numberOfCases; i++) {
  String size = stdin.readLineSync();
  String line = stdin.readLineSync();
  stringCase = line;
  var splitter = stringCase.split(" ");
  List<int> lint =splitter.map(int.parse).toList();
  troubleSort(lint);
  results.add("CASE #$i: ${checkSorted(troubleSort(lint))}");
  
  }
  for(var i = 0; i < results.length;i++){
    print(results[i]);
  }
}

troubleSort(paramArray){
  var len = paramArray.length;
  var swapped;
  do{
    swapped=false;
    for(var i = 0; i< len-2;i++){
      if(paramArray[i]>paramArray[i+2]){
        var tmp = paramArray[i];
        paramArray[i]=paramArray[i+2];
        paramArray[i+2]=tmp;
        swapped= true;
      }
    }
  }while(swapped);
  print(paramArray);
  return paramArray;
  
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

