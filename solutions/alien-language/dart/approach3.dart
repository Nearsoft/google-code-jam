import 'dart:io';

main() {

  new File('A-large-practice.in').readAsLines().then((List<String> lines) {
    var ldn = lines[0].split(" ");
    int D = int.parse(ldn[1]); 
    int N = int.parse(ldn[2]);
    
    var palabras = [];
    var patterns = [];

    for(int i=0; i<D; i++){
      palabras.add(lines[i+1]);
    }

    for(int i=0; i < N; i++){
      patterns.add(lines[i+D+1]);
      patterns[i] = patterns[i].replaceAll("(","[").replaceAll(")","]");
    }

    for(int i = 0; i < N; i++){
      int cont = 0;
      var pattern = new RegExp(patterns[i]);
      for(int j = 0; j < D; j++){
        if(pattern.hasMatch(palabras[j])){
          cont++;
        }
      }
      print("Case #${i+1}: ${cont}");
    }
  });
}
