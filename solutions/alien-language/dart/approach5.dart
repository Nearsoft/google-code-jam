import 'dart:io';
import 'dart:convert';
import 'dart:async';



List<String> list;
int wordsNumber;
int wordsLength;
int testCasesNumber;

void write(String message){
  var file = new File('file.txt');
  var sink = file.openWrite();
  sink.write(message);
  sink.close();
}

String main(){
  List<String> dictionary = new List();
  List<String> lines = new File('A-large-practice.in').readAsLinesSync();
  List<String> regexList = new List();
  var parametersString = lines[0];
  var parametersList = parametersString.split(' ');
  wordsLength = int.parse(parametersList[0]);
  wordsNumber = int.parse(parametersList[1]);
  testCasesNumber = int.parse(parametersList[2]);
  print (parametersList);
  print (wordsLength);
  print (wordsNumber);
  print (testCasesNumber);
  var file = new File('file.txt');
  var sink = file.openWrite();

  int i = 1;
  int testCaseCounter = 0;
  lines.removeAt(0);
  for (var line in lines) {
    if(i <= wordsNumber){
      dictionary.add(line);
    }if(i>wordsNumber && i < testCasesNumber + wordsNumber + 1){
      int testSuccessCounter = 0;
      testCaseCounter++;
      String regularExp = changeToRegEx(line);
      regexList.add(regularExp);
    }
    i++;
  }
  int j = 1;
  for(var regex in regexList){
    int count = 0;
    for(var word in dictionary){
      //print ("$word  --  $regex");
      if(word.contains(new RegExp(regex))){
        count ++;
      }
    }
    //print (regexList.length);
    print("Case #$j: $count");
    sink.write("Case #$j: $count\n");
    j++;
  }
  sink.close();
}

String changeToRegEx(String line){
  line = line.replaceAll(new RegExp(r'\('), '[');
  line = line.replaceAll(new RegExp(r'\)'), ']');
  return line;
}