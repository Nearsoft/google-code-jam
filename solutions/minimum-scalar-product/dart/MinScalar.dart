import 'dart:io';

main() {
  final filename = 'solution-large.txt';
  var sink = new File(filename).openWrite();

  new File('A-large-practice.in').readAsLines().then((List<String> lines) {
    num testCases = int.parse(lines[0]);
    
    for(int i=1; i<=testCases*3; i+=3){
      var v1 = lines[i+1].split(" "), v2 = lines[i+2].split(" ");
      int v1i = [], v2i = [], scalarProd = 0;

      for(int j = 0; j<int.parse(lines[i]); j++){
        v1i.add(int.parse(v1[j]));
        v2i.add(int.parse(v2[j]));
      }

      v1i.sort();
      v2i.sort();
      v2i = v2i.reversed.toList();
      for(int j = 0; j<int.parse(lines[i]); j++){
        scalarProd += v1i[j] * v2i[j];
      }
      sink.write("Case #${(i~/3)+1}: ${scalarProd}\n");
    }
  });
}