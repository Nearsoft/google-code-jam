import 'dart:io';

void main() {

  new File('A-large-practice.in').readAsLines().then((List<String> lines) {

    for (var i = 1; i < lines.length - 1; i+= 3) {
      var first_array = lines[i + 1].split(' ');
      var second_array = lines[i + 2].split(' ');

      var first_array_i = [], second_array_i =[];

      //Parsing the elements
      for (var j = 0; j < first_array.length; j++) {
        first_array_i.add(int.parse(first_array[j]));
        second_array_i.add(int.parse(second_array[j]));
      }

      //Sorting asc and desc
      first_array_i.sort((a, b) => a.compareTo(b));
      second_array_i.sort((a, b) => b.compareTo(a));

      var res = 0;
      for (var j = 0; j < first_array_i.length; j++) {
        res += first_array_i[j] * second_array_i[j];
      }
      //Printing the values
      print("Case #" + (((i/3)+ 1).toInt()).toString() + ": " + res.toString());

    }

    });

  }
