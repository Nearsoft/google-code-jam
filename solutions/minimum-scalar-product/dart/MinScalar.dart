import 'dart:io';

void main() {

    new File('/home/francisco/dart/google/bin/A-small-practice-minimum.in').readAsLines().then((List<String> input) {
       int numberOfVectors =  int.parse(input[0]);

       for (int i = 0; i < numberOfVectors; i++) {
           int elements = int.parse(input[3 * i + 1]);

           var vector1 = createArrayFromString( input[3 *i + 2] );
           var vector2 = createArrayFromString( input[3 *i + 3] );

           int result = 0;

           for (int j = 0; j < elements; j++) {
               result +=  vector1[j] * vector2[elements - j - 1];
           }

           print("Case #" + (i+1).toString() + ": " + result.toString());
       }
  });
}

createArrayFromString(String str) {
    var strArray = str.split(" ");
    var intArray = [];
    for (int i = 0; i < strArray.length; i++) {
      intArray.add(int.parse(strArray[i]));
    }
    intArray.sort();
    return intArray;
}
