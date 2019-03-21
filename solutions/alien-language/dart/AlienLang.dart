import 'dart:io';

main() {
  final filename = 'solution-small.txt';
  var sink = new File(filename).openWrite();

  new File('A-small-practice.in').readAsLines().then((List<String> lines) {
    var fileVariables = lines[0].split(" "), regExpr = [];
    int wordLength = int.parse(fileVariables[0]), languageLength = int.parse(fileVariables[1]), testCases = int.parse(fileVariables[2]); //L D N
    
    var languageWords = [];
    var solutions = new List(testCases);

    for(int i=0; i<languageLength; i++){ //Add the language that will be tested.
      languageWords.add(lines[i+1]);
    }

    for(int i=0; i<testCases; i++){ //Iterate through each String regex.
      regExpr.add(lines[i+languageLength+1]);
      regExpr[i] = regExpr[i].replaceAll("(","[").replaceAll(")","]");
    }

    for(int r = 0; r < regExpr.length; r++){
      regExpr[r] = new RegExp(regExpr[r]);
      solutions[r]=0;
      for(int t = 0; t <languageWords.length; t++){
        if(regExpr[r].hasMatch(languageWords[t])){
          solutions[r]++;
        }
      }
      sink.write("Case #${r+1}: ${solutions[r]}\n");
    }
  });
}
