import 'dart:io';

var keyboard = [
        ["a", "b", "c"],
        ["d", "e", "f"],
        ["g", "h", "i"],
        ["j", "k", "l"],
        ["m", "n", "o"],
        ["p", "q", "r", "s"],
        ["t", "u", "v"],
        ["w", "x", "y", "z"],
        [" "]
    ];


void main() {

    new File('/home/francisco/dart/google/bin/C-large-practice.in').readAsLines().then((List<String> input) {
       var numberOfWords =  int.parse(input[0]);

       var lastKey = "";

       for (var i = 1; i <= numberOfWords; i++) {
           var word = input[i];

           var str = "Case #" + i.toString() + ": ";

           for (var j = 0; j < word.length; j++) {
               var fromKeys = processLetter(charAt(word, j));

               if(charAt(fromKeys, 0) == lastKey){
                   str += " ";
               }

               lastKey = charAt(fromKeys, 0);
               str += fromKeys;
           }
           print(str);
       }
  });
}

String processLetter(String letter){
    var obj = getKey(letter);
    var key = obj['key'];
    var times = obj['times'];
    var str = "";
    for (var i = 0; i < times; i++) {
        str += key.toString();
    }
    return str;
}

getKey(String letter){
    for (var i = 0; i < keyboard.length; i++) {
        for (var j = 0; j < keyboard[i].length; j++) {
            if(keyboard[i][j] == letter){
                if(i == keyboard.length - 1){
                    return {
                        'key': 0,
                        'times': j+1
                    };
                }
                return {
                    'key': i+2,
                    'times': j+1
                };
            }
        }
    }
    return {
        'key': -1,
        'times': -1
    };
}

String charAt(String str, int idx) {
    return str[idx];
}
