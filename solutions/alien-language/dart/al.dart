import 'dart:io';

main() {
  final filename = 'solution-small.txt';
  var sink = new File(filename).openWrite();

  new File('A-small-practice.in').readAsLines().then((List<String> lines) {
    var languageWords = lines[0].split(" "), regExpr = [];
    int wordLength = int.parse(languageWords[0]), languageLength = int.parse(languageWords[1]), testCases = int.parse(languageWords[2]); //L D N
    languageWords = [];
    var caseN = new List(testCases);

    for(int i=0; i<languageLength; i++){ //Add the language that will be tested.
      languageWords.add(lines[i+1]);
    }

    for(int i=0; i<testCases; i++){ //Iterate through each String regex.
      bool flag = false, flag2=false;
      String tmp = "";
      regExpr.add(lines[i+languageLength+1]);

      for(int rune in regExpr[i].runes){
        if(rune==41){
          flag = false;
          tmp+=")";
          continue;
        }else if(rune==40){
          flag = true;
          flag2=false;
          tmp+="(";
          continue;
        }

        if(flag2 && flag){
          tmp+="|";
        }
        
        tmp+=new String.fromCharCode(rune);
        flag2=true;
      }

      regExpr[i]=tmp;
    }

    for(int r = 0; r < regExpr.length; r++){
      regExpr[r] = new RegExp(regExpr[r]);
      caseN[r]=0;
      for(int t = 0; t <languageWords.length; t++){
        if(regExpr[r].hasMatch(languageWords[t])){
          caseN[r]++;
        }
      }
      sink.write("Case #${r+1}: ${caseN[r]}\n");
    }
  });
}