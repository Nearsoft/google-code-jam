import 'dart:io';


void main() {

   var letters = {
    'a':"2",
    'b':"22",
    'c':"222",
    'd':"3",
    'e':"33",
    'f':"333",
    'g':"4",
    'h':"44",
    'i':"444",
    'j':"5",
    'k':"55",
    'l':"555",
    'm':"6",
    'n':"66",
    'o':"666",
    'p':"7",
    'q':"77",
    'r':"777",
    's':"7777",
    't':"8",
    'u':"88",
    'v':"888",
    'w':"9",
    'x':"99",
    'y':"999",
    'z':"9999",
    ' ':"0"
  };

 List<String> lines = new File('t9Example.text').readAsLinesSync();
    var file = new File('outputT9Dart.txt');
    var sink = file.openWrite();

  
   for(int i =1; i<lines.length; i++){
    var word = lines[i];
    
    var finalMessage = "";
    var last= "1";
  
  for(int i = 0 ; i< word.length; i++){
    var char = word[i];
    var number =letters[char][0];
    if(last == number){
      finalMessage = finalMessage+" ";
    }
      finalMessage = finalMessage+letters[char];
    last=number;
  }

    sink.write("case#$i: $finalMessage\n");
    //print(finalMessage);
  }
   sink.close();
}
