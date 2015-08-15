import 'dart:io';



void main() {

    List<String> lines = new File('example.text').readAsLinesSync();
    var file = new File('outputVectorDart.txt');
    var sink = file.openWrite();
    var caseNumber = 0;

      for(int i= 2; i<lines.length; i=i+3){
        
        var vector1 = lines[i].split(' ');
        var vector2 = lines[i+1].split(' ');
            var intVector1 =[];
            var intVector2 =[];
          for(int i=0; i<vector1.length; i++){
            intVector1.add(int.parse(vector1[i]));
          }
          for(int i=0; i<vector2.length; i++){
            intVector2.add(int.parse(vector2[i]));
          }

          intVector1.sort();
          intVector2.sort();
          intVector2 = intVector2.reversed.toList();

          var total = 0;
          for(int i = 0; i< intVector1.length ; i++){
              var sum =intVector1[i]*intVector2[i];
              total +=sum; 
               
          }
           caseNumber += 1;
      sink.write("Case #$caseNumber: $total\n");
    }
  sink.close();
}
