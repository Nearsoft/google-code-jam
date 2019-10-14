import 'dart:async';
import 'dart:io';
import 'dart:convert';

var i = 0;
int nCases = 0;
var c = 1;

main() {
  var stream = stdin
    .transform(utf8.decoder)
    .transform(new LineSplitter())
    .forEach((l) => {handleCase(l)});
}

handleCase(String l) {
  if ((i > 0) && (i % 2 == 0)) {
    List<int> nums = l.split(" ").map(int.parse).toList();
   print("Case #${c}: ${separateArray(nums)}");
   c++;
  }
  i++;
}

separateArray(array){
  var size = array.length;
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
  for(var i=0; i<size-1;i++){
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