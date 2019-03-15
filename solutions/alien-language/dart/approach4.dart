import 'dart:io';

void main() {

    new File('/home/francisco/dart/google/bin/A-small-practice.in').readAsLines().then((List<String> input) {
      var letters = input[0];

      //sacar L, D y N
      var L = getIntFromString(letters, 0);
      var D = getIntFromString(letters, 1);
      var N = getIntFromString(letters, 2);

      var words = createArray(input, 1, D + 1);

      var tokens = createArray(input, D + 1, D + 1 + N);
      for (var i = 0; i < tokens.length; i++) {
        print("Case #" + (i + 1).toString() + ": " + match(tokens[i], words).toString());
      }
  });
}

int match(String token, words) {
  var counter = 0;

  for (var j = 0; j < words.length; j++) {
    var regex = new RegExp(token.replaceAll("(", "[").replaceAll(")", "]"));
    if (regex.hasMatch(words[j])) {
      counter++;
    }
  }
  return counter;
}

createArray(input, int min, int max) {
  var array = [];
  for (var i = min; i < max; i++) {
    array.add(input[i]);
  }
  return array;
}

int getIntFromString(String str, int idx) {
  return int.parse(str.split(" ")[idx]);
}
