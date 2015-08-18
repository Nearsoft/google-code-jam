import 'dart:io';

main() {

  var alphabet = [" ", "", "abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"];

  new File('A-small-practice.in').readAsLines().then((List<String> lines) {
    
    var n = int.parse(lines[0]);

    for (var i = 1; i < n; i++){

        var anterior = -1;
        var palabra = lines[i];
        stdout.write("Case #${i}: ");

        for(var j = 0; j < palabra.length; j++) {
            for(var k = 0; k < alphabet.length; k++) {
                for(var l = 0; l < alphabet[k].length; l++) {

                if(palabra[j].toString() == alphabet[k][l]) {

                    if (anterior == k) {

                        stdout.write(" ");

                    }

                    for (var m = 0; m <= l; m++) {

                    stdout.write(k);
                    anterior = k;

                    }

                }

                }
            }
        }
    print("");

    }
  });
    }


