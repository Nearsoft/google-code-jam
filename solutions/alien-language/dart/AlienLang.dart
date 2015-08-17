import 'dart:io';

void main() {

  new File('A-small-practice.in').readAsLines().then((List<String> lines) {


    var dictionary_length = int.parse(lines[0].split(' ')[1]);
    var dictionary = [];
    //Filling up the dictionary
    for (var i = 1; i < dictionary_length + 1; i++) {
      dictionary.add(lines[i]);
    }

    for (var i = dictionary_length + 1; i < lines.length; i++) {

      var test_case = lines[i];
      test_case =  test_case.replaceAll('(', '['); // 'résumé'
      test_case =  test_case.replaceAll(')', ']'); // 'résumé'

      var regexp = new RegExp(test_case);
      var res = 0;

      //Cheking if the regex matches a word from the dictionary
      for (var j = 0; j < dictionary_length; j++) {
        var str = dictionary[j];
        if (regexp.hasMatch(str)) res += 1;
      }

      //Printing the result
      print("Case #" + (i - dictionary_length).toString() + ": " + res.toString());
    }

    });

  }
