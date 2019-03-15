import 'dart:io';

main() {

  new File('A-large-practice.in').readAsLines().then((List<String> lines) {
    
    var T = int.parse(lines[0]);

    for (int i = 1; i < T*3; i+=3) {
    
    int mult = 0;
    int n = int.parse(lines[i]);

    var vector1str = lines[i+1].split(" ");
    var vector2str = lines[i+2].split(" ");

    int vector1 = [];
    int vector2 = [];

    for(int i=0; i < n; i++){
      vector1.add(int.parse(vector1str[i]));
      vector2.add(int.parse(vector2str[i]));
    }

    vector1.sort();
    vector2.sort();
    vector2 = vector2.reversed.toList();

    for(int i=0; i < n; i++){
      mult+= vector1[i] * vector2[i];
    }
  
      print("Case #${i+1}: ${mult}");
  
    }
  });
}
