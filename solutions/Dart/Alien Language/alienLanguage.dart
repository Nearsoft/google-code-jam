import "dart:io";
void main() {

  final filename ='solution-large.txt';
  var sink = new File(filename).openWrite();
  List<String> lines = new File('A-large-practice.in').readAsLinesSync();
  List<String> words = [];
  List<String> patterns = [];
  List<int> header = lines[0].split(" ");
  
  int i = 1;
  while(i<= int.parse(header[1])){
    words.add(lines[i]); 
    i++;
  }

  while(i<= int.parse(header[2])+int.parse(header[1])){
    patterns.add(lines[i]); 
    i++;
  }

  int caso = 1;
  for (var pattern in patterns){
    pattern = pattern.replaceAll("(", "[");
    pattern = pattern.replaceAll(")", "]");

    int counter =0;
    for(var word in words){
      if(word.contains((new RegExp(pattern)))){
  			counter++;
      }
    }
    sink.write("Case #$caso: $counter\n");
    caso++;
  }



}
