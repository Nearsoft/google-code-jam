import 'dart:io';

void main() {
  var numpad = [
  " ",
  "",
  "abc",
  "def",
  "ghi",
  "jkl",
  "mno",
  "pqrs",
  "tuv",
  "wxyz"
  ];

  new File('C-small-practice.in').readAsLines().then((List<String> lines) {
    var word = lines;
      for (var i = 1; i < word.length; i++) {

        var res = '';
        var prev_char = '';

        for (var j = 0; j < word[i].length; j++) {
          var char =  word[i][j];

          for (var k = 0; k < numpad.length; k++) {
              if (numpad[k].indexOf(char) > -1) {
                  if (prev_char == k) res += ' ';
                  var num_index = numpad[k].indexOf(char);

                  for (var l = 0; l < num_index + 1; l++) {
                    res += k.toString();
                  }

                  prev_char = k;
              }
          }
        }
        print("Case #" + i.toString() + ": " +res);
      }
    });
  }
