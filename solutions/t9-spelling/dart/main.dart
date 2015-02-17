import 'dart:io';

main() {
  final filename = 'solution.txt';
  var sink = new File(filename).openWrite();
  
  var keyOf = {
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

  new File('C-large-practice.in').readAsLines().then((List<String> lines) {
    num testCases = int.parse(lines[0]);
    String ans,prev;

    for(int i=1; i<=testCases; i++){ //Number of words.
      prev=ans="";
      
      lines[i].runes.forEach((int rune) { //Iterate through each letter of the current word/line.
        String letterKey = keyOf[new String.fromCharCode(rune)];
        if(prev==letterKey[0]){
          ans+=" ";
        } 
        ans+="${letterKey}";
        prev=letterKey[0];
      });
      sink.write("Case #${i}: ${ans}\n");
    }
  });
  //sink.close();
}