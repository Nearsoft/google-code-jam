import 'dart:io';



void main(){

    List<String> lines = new File('exampleAlien.text').readAsLinesSync();
    var file = new File('outputAlienDart.txt');
    var sink = file.openWrite();

    var indexArray =[];
    var dictionarySize = 0;
    var caseSize = 0;
    var dictionary =[];
    var cases = [];
    var y = 0;
    var caseNumber= 0;

     
      

    while(y< lines.length-1){
  
      
       indexArray =lines[y].split(' ');
       dictionarySize = int.parse(indexArray[1]);
       caseSize = int.parse(indexArray[2]);
    

    
       for(int a = y+1; a<dictionarySize+1; a++){//working
        dictionary.add(lines[a]);
       }
       for(int b = y+dictionarySize+1; b<caseSize+dictionarySize+1; b++){  
           
        cases.add(lines[b]);
       }
      
  var pattern = "";
  
  var counter =0;
  
  
   
  for (int i = 0; i < cases.length; i++) {
      pattern = cases[i].replaceAll('(','[').replaceAll(')',']');
       var regExp = new RegExp(pattern);
        
        for(int j = 0; j< dictionary.length; j++){
            if(regExp.hasMatch(dictionary[j])){
            counter++;
            }// if
        }//for2
          //var output= "case#"+(caseNumber).toString()+": "+counter.toString();
        
        //print(output);

        sink.write("case#$caseNumber: $counter\n");
        counter=0;
        caseNumber=caseNumber+1;
  }

      
  
    y=y+caseSize+dictionarySize+1;
     
  }
   
    
  // sink.close();
  
}