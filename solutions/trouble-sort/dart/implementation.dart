import 'dart:io';
import 'dart:convert';
Future main(List<String> args) async{
  /**
   * Install dart
   * In your command line type dart implementation.dart <filename of the tests>
   * Watch the magic happen in your console 😎
   */

  // read the location of the file as an argument 
  var config = File(args[0]);
  // Store the test cases
  List<List<int>> testCases = [];
  String stringCase;
  Stream<List<int>> inputStream = config.openRead();
  var flag=0;
  var lines =
      utf8.decoder.bind(inputStream).transform(LineSplitter());
  try {
    int numberOfCases;
    /**
     * Here's an implementation that im not proud of but it works and 
     * since the problem gives first the test in a funky way i made 
     * it work heres an example of how it gives the test
     * 2          Number of test cases
     * 4          length of the array
     * 2 3 4 5    array
     * 3          length of the array
     * 8 9 7      array
     * 
     * So I read the first line (hence the flag) and then read in duplets
     * 
     */
    await for (var line in lines) {
      // first is time of array second size of array and on and on
      if(flag==0){
      flag=1;
      }else if(flag==1){
        // getting size of array
        flag=2;
      } else {
        // getting array and printing if okay n stuff
          stringCase = line;
          var splitter = stringCase.split(" ");
          List<int> lint = splitter.map(int.parse).toList();
          testCases.add(lint);
        flag=1;
      }

    }
  } catch (e) {
    print(e);
  }

  for(int i = 0; i < testCases.length; i++){
    print("Case #${i+1} ${checkSorted(troubleSort(testCases[i]))} Array result: ${troubleSort(testCases[i])}");
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

